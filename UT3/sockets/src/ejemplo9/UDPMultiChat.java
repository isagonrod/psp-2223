package ejemplo9;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class UDPMultiChat extends JFrame implements ActionListener, Runnable {
    static MulticastSocket ms = null;
    static byte[] buf = new byte[1000];
    static InetAddress grupo = null;
    static int puerto = 12345; // puerto multicast

    String nombre;
    static JTextField mensaje = new JTextField();
    static JTextArea textarea;
    JButton boton = new JButton("Enviar");
    JButton desconectar = new JButton("Salir");
    boolean repetir = true;

    public UDPMultiChat(String nombre) {
        super("CONEXIÓN DEL CLIENTE CHAT: " + nombre);
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);

        textarea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textarea);
        scrollPane.setBounds(10, 50, 400, 300);
        add(scrollPane);

        boton.setBounds(420, 10, 100, 30);
        add(boton);
        desconectar.setBounds(420, 50, 100, 30);
        add(desconectar);

        textarea.setEditable(false);
        boton.addActionListener(this);
        desconectar.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.nombre = nombre;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == boton) { // Se pulsa "Enviar"
            String texto = nombre + " >> " + mensaje.getText();
            try {
                // Enviando mensaje al grupo
                DatagramPacket packet = new DatagramPacket(texto.getBytes(), texto.length(), grupo, puerto);
                ms.send(packet);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == desconectar) { // Se pulsa "Salir"
            String texto = "*** Abandona el chat: " + nombre + " ***";
            try {
                // Enviando despedida al grupo
                DatagramPacket packet = new DatagramPacket(texto.getBytes(), texto.length(), grupo, puerto);
                ms.send(packet);
                ms.close();
                repetir = false;
                System.out.println("Abanda el chat: " + nombre);
                System.exit(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (repetir) {
            try {
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                ms.receive(p); // recibo mensajes
                String texto = new String(p.getData(), 0, p.getLength());
                textarea.append(texto + "\n");
            } catch (SocketException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick: ");

        // Se crea el socket multicast
        ms = new MulticastSocket(puerto);
        grupo = InetAddress.getByName("225.0.0.1"); // Grupo multicast

        // Nos unimos al grupo
        ms.joinGroup(grupo);

        if (!nombre.trim().equals("")) {
            UDPMultiChat server = new UDPMultiChat(nombre);
            server.setBounds(0, 0, 540,400);
            server.setVisible(true);
            new Thread(server).start(); // lanzar hilo
        } else {
            System.out.println("El nombre está vacío...");
        }
    }
}
