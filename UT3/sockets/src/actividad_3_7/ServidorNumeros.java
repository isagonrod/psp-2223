package actividad_3_7;

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

        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
        Numeros numCliente = new Numeros();
        int num = Integer.parseInt(String.valueOf(inObjeto.readObject()));
        System.out.println("Recibo: " + num);

        while (num > 0) {
            ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
            Numeros numServidor = new Numeros(num, calcularCuadrado(numCliente.getNumero()), calcularCubo(numCliente.getNumero()));
            outObjeto.writeObject(numServidor);
            System.out.println("Envío: " + numServidor.getNumero() + " -> Cuadrado: " + numServidor.getCuadrado() + ", Cubo: " + numServidor.getCubo());

            inObjeto.close();
            outObjeto.close();
            cliente.close();

        }
        servidor.close();
    }

    public static long calcularCuadrado(int num) {
        return (long) num * num;
    }

    public static long calcularCubo(int num) {
        return (long) num * num * num;
    }
}
