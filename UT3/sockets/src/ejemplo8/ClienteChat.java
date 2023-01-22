package ejemplo8;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteChat extends JFrame implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;
    Socket socket = null;

    // Streams
    DataInputStream fentrada; // Para leer los mensajes
    DataOutputStream fsalida; // Para escribir los mensajes

    String nombre;
    static JTextField mensaje = new JTextField();

    private JScrollPane scrollPane;
    static JTextArea textArea;
    JButton botonEnviar = new JButton("Enviar");
    JButton botonSalir = new JButton("Salir");
    boolean repetir = true;

    // Constructor
    public ClienteChat(Socket s, String nombre) {
        super("CONEXIÓN DEL CLIENTE CHAT: " + nombre);
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 50, 400, 300);
        add(scrollPane);

        botonEnviar.setBounds(420, 10, 100, 30);
        add(botonEnviar);
        botonSalir.setBounds(420, 50, 100, 30);
        add(botonSalir);

        textArea.setEditable(false);
        botonEnviar.addActionListener(this);
        botonSalir.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.socket = s;
        this.nombre = nombre;

        try {
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
            String texto = " > Entra en el chat... " + nombre;
            fsalida.writeUTF(texto);
        } catch (IOException ex) {
            System.out.println("ERROR DE E/S");
            ex.printStackTrace();
            System.exit(0);
        }
    }

    // Acción cuando pulsamos botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonEnviar) { // Se pulsa "Enviar"
            if (mensaje.getText().trim().length() == 0) {
                String texto = nombre + " > " + mensaje.getText();

                try {
                    mensaje.setText("");
                    fsalida.writeUTF(texto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == botonSalir) { // Se pulsa "Salir"
            String texto = " > Abandona el chat... " + nombre;

            try {
                fsalida.writeUTF(texto);
                fsalida.writeUTF("*");
                repetir = false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        String texto = "";

        while (repetir) {
            try {
                texto = fentrada.readUTF(); // leer mensajes
                textArea.setText(texto); // visualizarlos
            } catch (IOException ex) {
                // Este error sale cuando el servidor se cierra
                JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n"
                        + ex.getMessage(), "<<MENSAJE DE ERROR:2>>", JOptionPane.ERROR_MESSAGE);
                repetir = false; // Salir del bucle
            }
        }
        try {
            socket.close(); // cerrar socket
            System.exit(0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 44444;
        Socket s = null;

        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick: ");

        if (nombre.trim().length() == 0) {
            System.out.println("El nombre está vacío...");
            return;
        }

        try {
            s = new Socket("localhost", port);
            ClienteChat cliente = new ClienteChat(s, nombre);
            cliente.setBounds(0, 0, 540, 400);
            cliente.setVisible(true);
            new Thread(cliente).start(); // lanzar hilo cliente
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n"
                    + ex.getMessage(), "<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
        }
    }
}
