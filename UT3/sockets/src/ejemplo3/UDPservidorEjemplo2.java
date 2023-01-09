package ejemplo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPservidorEjemplo2 {
    public static void main(String[] args) throws IOException {

        // ASOCIAR EL SOCKET AL PUERTO 12345
        DatagramSocket socket = new DatagramSocket(12345);

        // ESPERAR DATAGRAMA
        System.out.println("Servidor Esperando Datagrama................ ");
        DatagramPacket recibo;

        byte[] bufer = new byte[1024];
        recibo = new DatagramPacket(bufer, bufer.length);
        socket.receive(recibo);

        String mensaje = new String(recibo.getData()).trim();
        System.out.println("Servidor Recibe: " + mensaje);

        // CONTAR EL NÚMERO DE LETRAS 'a'
        int contador = 0;
        for (int i = 0; i < mensaje.length(); i++) {
            if (mensaje.charAt(i) == 'a') {
                contador++;
            }
        }

        // DIRECCIÓN ORIGEN DEL MENSAJE
        InetAddress IPorigen = recibo.getAddress();
        int puerto = recibo.getPort();

        // ENVIAR DATAGRAMA AL CLIENTE
        System.out.println("Enviando número de apariciones de la letra 'a' => " + contador);
        byte b = (byte)contador; // Paso entero a byte
        byte[] enviados = new byte[1024];
        enviados[0] = b;

        DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPorigen, puerto);
        socket.send(envio);

        // CERRAR SOCKET
        System.out.println("Cerrando conexión....... ");
        socket.close();
    }
}
