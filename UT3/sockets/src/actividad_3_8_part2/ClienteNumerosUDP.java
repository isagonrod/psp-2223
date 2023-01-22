package actividad_3_8_part2;

import actividad_3_7.Numeros;
import util.Teclado;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;

public class ClienteNumerosUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 6000;
        InetAddress ipServer = InetAddress.getLocalHost(); // localhost

        DatagramSocket client = null;

        try {
            client = new DatagramSocket();
            System.out.println("PROGRAMA CLIENTE INICIADO...");
        } catch (ConnectException ex) {
            System.out.println("ERROR AL ESTABLECER LA CONEXIÓN CON EL SERVIDOR...");
            System.exit(0);
        }

        int number = 0;

        do {
            try {
                number = Teclado.getNum("Número: ");
            } catch (InputMismatchException ex) {
                number = 1;
                System.out.println("\nNúmero incorrecto...");
                continue;
            }

            Numeros numeros = new Numeros();
            numeros.setNumero(number);

            // Convertimos objeto a bytes
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOut);

            out.reset();
            out.writeObject(numeros); // escribir objetos
            byte[] sent = byteArrayOut.toByteArray(); // objeto a bytes

            // enviar objeto
            DatagramPacket packet = new DatagramPacket(sent, sent.length, ipServer, port);
            client.send(packet);

            // Se recibe un objeto
            if (number > 0) {
                byte[] received = new byte[1024];
                DatagramPacket packetReceived = new DatagramPacket(received, received.length);
                client.receive(packetReceived); // recibo el datagrama

                // Convertimos bytes a objeto
                ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(received);
                ObjectInputStream in = new ObjectInputStream(byteArrayIn);

                Numeros num = new Numeros();
                num = (Numeros) in.readObject(); // obtengo objetos
                in.close();

                System.out.println("\nCuadrado: " + num.getCuadrado() + ", Cubo: " + num.getCubo());
            }
        } while (number > 0);

        System.out.println("CLIENTE UDP FINALIZADO...");

        // Cerrar Streams y Sockets
        client.close();
    }
}
