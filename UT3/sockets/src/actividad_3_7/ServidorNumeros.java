package actividad_3_7;

import util.Calculadora;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
    }
}