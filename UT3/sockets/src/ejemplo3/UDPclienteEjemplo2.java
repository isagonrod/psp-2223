package ejemplo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPclienteEjemplo2 {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();

        // DATOS DEL SERVIDOR AL QUE ENVIAR MENSAJE
        InetAddress IPservidor = InetAddress.getLocalHost();
        int puerto = 12345; // Puerto por el que escucha

        // INTRODUCIR DATOS POR TECLADO
        System.out.println("Introduce mensaje: ");
        String cadena = sc.nextLine();

        byte[] enviados = new byte[1024];
        enviados = cadena.getBytes();

        // ENVIAR DATAGRAMA AL SERVIDOR
        DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPservidor, puerto);

        clientSocket.send(envio);

        // RECIBIR DATAGRAMA DEL SERVIDOR
        byte[] recibidos = new byte[2];
        DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
        System.out.println("Esperando datagrama................ ");
        clientSocket.receive(recibo);

        // Obtener el número de caracteres
        byte[] hh = recibo.getData();
        int numero = hh[0];

        System.out.println("Recibo Nº de caracteres que son a => " + numero);
        clientSocket.close(); // Cerrar socket
    }
}
