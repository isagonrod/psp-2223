package ejemplo4;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCcliente {
    public static void main(String[] args) throws Exception {
        // Se crea el socket multicast
        int puerto = 12345; // Puerto multicast
        MulticastSocket ms = new MulticastSocket(puerto);

        InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Grupo

        // Nos unimos al grupo
        ms.joinGroup(grupo);

        String message = "";

        while(!message.trim().equals("*")) {
            byte[] buf = new byte[1000];

            // Recibe el paquete del servidor
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            ms.receive(paquete);

            message = new String(paquete.getData());
            System.out.println("Recibo: " + message.trim());
        }
        ms.leaveGroup(grupo);
        ms.close();
        System.out.println("Socket cerrado...");
    }
}
