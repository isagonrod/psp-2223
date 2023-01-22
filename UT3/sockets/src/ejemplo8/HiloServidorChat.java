package ejemplo8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidorChat extends Thread {
    DataInputStream fentrada;
    Socket socket = null;
    ComunHilos comun;

    public HiloServidorChat(Socket s, ComunHilos comun) {
        this.socket = s;
        this.comun = comun;

        try {
            // Creo flujo de entrada para leer los mensajes
            fentrada = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("ERROR DE E/S");
            ex.printStackTrace();
        }
    }

    public void run() {
        System.out.println("NÚMERO DE CONEXIONES ACTUALES: " + comun.getActuales());

        // Nada más conectarse le envío todos los mensajes
        String texto = comun.getMensajes();
        EnviarMensajesATodos(texto);

        while (true) {
            String cadena = "";

            try {
                cadena = fentrada.readUTF();

                if (cadena.trim().equals("*")) { // Cliente desconecta
                    comun.setActuales(comun.getActuales() - 1);
                    System.out.println("NÚMERO DE CONEXIONES ACTUALES: " + comun.getActuales());
                    break; // sale del bucle
                }

                comun.setMensajes(comun.getMensajes() + cadena + "\n");
                EnviarMensajesATodos(comun.getMensajes());
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }

        // Se cierra el socket del cliente
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Envía los mensajes del chat a los clientes
    private void EnviarMensajesATodos(String texto) {
        // Recorremos tabla de sockets para enviarles los mensajes
        for (int i = 0; i < comun.getConexiones(); i++) {
            Socket s1 = comun.getElementoTabla(i);
            if (!s1.isClosed()) {
                try {
                    DataOutputStream fsalida2 = new DataOutputStream(s1.getOutputStream());
                    fsalida2.writeUTF(texto);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
