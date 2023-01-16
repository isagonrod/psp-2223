package actividad_3_8_part2;

/*
 * ACTIVIDAD 3.8 (página 150 del libro)
 *
 * Parte 2:
 * Realiza la Actividad 3.7 con sockets UDP.
 */

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorNumerosUDP {
    public static void main(String[] args) throws Exception {
        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("Esperando al cliente...");
        Socket cliente = servidor.accept();
    }
}
