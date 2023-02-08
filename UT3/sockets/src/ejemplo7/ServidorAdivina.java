package ejemplo7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAdivina {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6001);
        System.out.println("Servidor iniciado...");

        // Número a adivinar entre 1 y 25
        int num = (int)(Math.random() * 25 + 1);
        System.out.println("NÚMERO A ADIVINAR => " + num);

        // Todos los hilos comparten el objeto
        ObjetoCompartido objet = new ObjetoCompartido(num);
        int id = 0;
        while (true) {
            Socket cliente = new Socket();
            cliente = server.accept(); // esperando cliente
            id++; // identificador para el cliente
            HiloServidorAdivina hilo = new HiloServidorAdivina(cliente, objet, id);
            hilo.start();
        }
    }
}
