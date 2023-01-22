package actividad_3_7;

import util.Teclado;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.InputMismatchException;

public class ClienteNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String host = "localhost";
        int port = 6000; // puerto remoto

        Socket client = null;

        try {
            client = new Socket(host, port);
            System.out.println("PROGRAMA CLIENTE INICIADO...");
        } catch (ConnectException ex) {
            System.out.println("ERROR AL ESTABLECER LA CONEXIÓN CON EL SERVIDOR...");
            System.exit(0);
        }

        // Flujo de entrada para objetos
        ObjectInputStream numIn = new ObjectInputStream(client.getInputStream());

        // Flujo de salida para objetos
        ObjectOutputStream numOut = new ObjectOutputStream(client.getOutputStream());

        int number = 1;

        do {
            try {
                number = Teclado.getNum("Número: ");
            } catch (InputMismatchException ex) {
                number = 1; // se asigna un valor mayor a 0 para poder pedir otro número
                System.out.println("\nNúmero incorrecto...\n");
                continue;
            }
            Numeros numeros = new Numeros();
            numeros.setNumero(number);

            // Se envía el objeto
            numOut.writeObject(numeros);

            // Se recibe un objeto
            if (number > 0) {
                Numeros num = (Numeros) numIn.readObject(); // recibo objeto

                System.out.println("Cuadrado: " + num.getCuadrado() + ", Cubo: " + num.getCubo());
            }
        } while (number > 0);

        System.out.println("CLIENTE FINALIZADO...");

        // Cerrar Streams y Sockets
        numIn.close();
        numOut.close();
        client.close();

        /* -- MI SOLUCIÓN --
        String host = "localhost";
        int puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, puerto);

        ObjectOutputStream outObjeto;

        Numeros numeros = new Numeros(Teclado.getNum("\nNúmero: "));

        while (numeros.getNumero() > 0) {
            outObjeto = new ObjectOutputStream(cliente.getOutputStream());
            outObjeto.writeObject(numeros);
            System.out.println("Envío: " + numeros.getNumero());

            ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
            Numeros number = (Numeros) inObjeto.readObject();

            System.out.println("Recibo: " + number.getNumero() + " -> Cuadrado: " + number.getCuadrado() + ", Cubo: " + number.getCubo());

            numeros = new Numeros(Teclado.getNum("\nNúmero: "));
        }

        cliente.close();
        */
    }
}