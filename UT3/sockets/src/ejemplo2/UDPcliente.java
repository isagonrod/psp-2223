package ejemplo2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPcliente {
    public static void main(String[] args) throws Exception {
        InetAddress destino = InetAddress.getLocalHost();
        int port = 12345;
        byte[] mensaje = new byte[1024];
        String saludo = "Enviando Saludos!";
        mensaje = saludo.getBytes();

        // CONSTRUIR EL DATAGRAMA A ENVIAR
        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
        DatagramSocket socket = new DatagramSocket(34567); // Puerto local

        System.out.println(""
                + "\nEnviando Datagrama de longitud         : " + mensaje.length
                + "\nHost destino                           : " + destino.getHostName()
                + "\nIP Destino                             : " + destino.getHostAddress()
                + "\nPuerto local del socket                : " + socket.getLocalPort()
                + "\nPuerto al que envío                    : " + envio.getPort());

        // ENVIAR DATAGRAMA
        socket.send(envio);
        socket.close(); // Cierro el socket
    }
}
