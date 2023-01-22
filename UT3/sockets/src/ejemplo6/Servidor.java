package ejemplo6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6000);
        System.out.println("Servidor iniciado...");

        while (true) {
            Socket client = new Socket();
            client = server.accept(); // esperando cliente
            HiloServidor hilo = new HiloServidor(client);
            hilo.start(); // se atiende al cliente
        }
    }
}
