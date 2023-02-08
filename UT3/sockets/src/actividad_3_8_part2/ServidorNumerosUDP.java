package actividad_3_8_part2;

/*
 * ACTIVIDAD 3.8 (página 150 del libro)
 *
 * Parte 2:
 * Realiza la Actividad 3.7 con sockets UDP.
 */

import actividad_3_7.Numeros;
import util.Calculadora;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorNumerosUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 6000;
        DatagramSocket serverSocket = new DatagramSocket(port);

        Numeros number = new Numeros();
        number.setNumero(1);

        System.out.println("SERVIDOR UDP INICIADO...");

        while (number.getNumero() > 0) {

            // Recibo datagrama
            number = new Numeros();
            byte[] received = new byte[1024];
            DatagramPacket packetReceived = new DatagramPacket(received, received.length);
            serverSocket.receive(packetReceived);

            // Convertimos bytes a objeto
            ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(received);
            ObjectInputStream in = new ObjectInputStream(byteArrayIn);
            number = (Numeros) in.readObject(); // obtengo objeto

            number.setCuadrado(Calculadora.calcularCuadrado(number.getNumero()));
            number.setCubo(Calculadora.calcularCubo(number.getNumero()));

            System.out.println("Recibo: " + number.getNumero());

            // Dirección origen
            InetAddress ipSource = packetReceived.getAddress();
            int portSource = packetReceived.getPort();

            // Convertimos objeto a bytes
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOut);
            out.reset();
            out.writeObject(number);

            byte[] bytes = byteArrayOut.toByteArray(); // objeto en bytes

            // Envío datagrama
            DatagramPacket packetSent = new DatagramPacket(bytes, bytes.length, ipSource, portSource);
            serverSocket.send(packetSent);
        }

        System.out.println("SERVIDOR UDP FINALIZADO...");

        // Cerrar Streams y Sockets
        serverSocket.close();
    }
}
