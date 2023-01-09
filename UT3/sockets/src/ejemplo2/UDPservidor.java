package ejemplo2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPservidor {
    public static void main(String[] args) throws Exception {
        byte[] bufer = new byte[1024]; // Bufer para recibir el datagrama

        // ASOCIAR EL SOCKET AL PUERTO 12345
        DatagramSocket socket = new DatagramSocket(12345);

        // ESPERAR DATAGRAMA
        System.out.println("Esperando Datagrama................ ");
        DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);
        socket.receive(recibo); // Recibo datagrama
        int bytesRec = recibo.getLength(); // Obtengo número de bytes
        String paquete = new String(recibo.getData());

        // VISUALIZAR INFORMACIÓN
        System.out.println(""
                + "\nNúmero de Bytes recibidos          : " + bytesRec
                + "\nContenido del Paquete              : " + paquete.trim()
                + "\nPuerto origen del mensaje          : " + recibo.getPort()
                + "\nIP de origen                       : " + recibo.getAddress().getHostAddress()
                + "\nPuerto destino del mensaje         : " + socket.getLocalPort());
        socket.close(); // Cierro el socket
    }
}
