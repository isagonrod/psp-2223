package actividad_3_7;

import util.Teclado;

import java.io.*;
import java.net.Socket;

public class ClienteNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, puerto);

        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

        Numeros numeros = new Numeros(Teclado.getNum("\nNúmero: "));

        while (numeros.getNumero() > 0) {
            outObjeto.writeObject(numeros);
            System.out.println("Envío: " + numeros.getNumero());

            ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
            Numeros number = (Numeros) inObjeto.readObject();

            System.out.println("Recibo: " + number.getNumero() + " -> Cuadrado: " + number.getCuadrado() + ", Cubo: " + number.getCubo());

            outObjeto.close();
            inObjeto.close();

            numeros = new Numeros(Teclado.getNum("\nNúmero: "));
        }

        cliente.close();
    }
}
