package actividad_3_8_part2;

import java.net.Socket;

public class ClienteNumerosUDP {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, puerto);
    }
}
