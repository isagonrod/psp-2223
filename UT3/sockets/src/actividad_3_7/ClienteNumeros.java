package actividad_3_7;

import java.io.*;
import java.net.Socket;

public class ClienteNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, puerto);

        Numeros numeros = new Numeros();

        BufferedReader num = (new BufferedReader(new InputStreamReader(System.in)));
        int number = Integer.parseInt(String.valueOf(num));

        while(number > 0) {
            numeros.setNumero(number);

            ObjectOutputStream numSalida = new ObjectOutputStream(cliente.getOutputStream());
            numSalida.writeObject(numeros);
            System.out.println("Envío: " + numeros.getNumero());

            ObjectInputStream numEntrada = new ObjectInputStream(cliente.getInputStream());
            Numeros numServer = (Numeros) numEntrada.readObject();
            System.out.println("Recibo: " + numServer.getNumero() + " -> Cuadrado: " + numServer.getCuadrado() + ", Cubo: " + numServer.getCubo());

            numSalida.close();
            cliente.close();

            num = (new BufferedReader(new InputStreamReader(System.in)));
            number = Integer.parseInt(String.valueOf(num));
        }

    }
}
