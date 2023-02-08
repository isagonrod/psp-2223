package actividad_3_7;

import util.Calculadora;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServidorNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int port = 6000; // puerto
        ServerSocket server = new ServerSocket(port);

        System.out.println("Esperando al cliente...");
        Socket client = server.accept();

        // Se prepara un flujo de salida para objetos
        ObjectOutputStream outNum = new ObjectOutputStream(client.getOutputStream());

        // Se obtiene un Stream para leer objetos
        ObjectInputStream inNum = new ObjectInputStream(client.getInputStream());

        Numeros num = new Numeros();

        try {
            num = (Numeros) inNum.readObject();
        } catch (SocketException ex) {
            System.out.println("ERROR AL RECIBIR DATOS DEL CLIENTE... " + ex.getMessage());
            System.exit(0);
        }

        while (num.getNumero() > 0) {
            num.setCuadrado(Calculadora.calcularCuadrado(num.getNumero()));
            num.setCubo(Calculadora.calcularCubo(num.getNumero()));

            System.out.println("Recibo: " + num.getNumero());
            outNum.writeObject(num); // enviando objeto

            try {
                num = (Numeros) inNum.readObject();
            } catch (SocketException ex) {
                System.out.println("ERROR AL RECIBIR DATOS DEL CLIENTE... " + ex.getMessage());
                System.exit(0);
            }
        }

        System.out.println("SERVIDOR FINALIZADO...");

        // Cerrar Streams y Sockets
        outNum.close();
        inNum.close();
        client.close();
        server.close();

        /* -- MI SOLUCIÓN --

        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("Esperando al cliente...");
        Socket cliente = servidor.accept();

        ObjectInputStream numEnt;

        Numeros number = new Numeros(1);

        while (number.getNumero() > 0) {
            numEnt = new ObjectInputStream(cliente.getInputStream());
            number = (Numeros) numEnt.readObject();
            System.out.println("Recibo: " + number.getNumero());

            number.setCuadrado(Calculadora.calcularCuadrado(number.getNumero()));
            number.setCubo(Calculadora.calcularCubo(number.getNumero()));

            ObjectOutputStream numSal = new ObjectOutputStream(cliente.getOutputStream());
            numSal.writeObject(number);
            System.out.println("Envío: " + number.getNumero() + " -> Cuadrado: " + number.getCuadrado() + ", Cubo: " + number.getCubo());

        }
        cliente.close();
        servidor.close();

        */
    }
}