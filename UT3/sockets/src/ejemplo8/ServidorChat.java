package ejemplo8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat {
    static final int MAXIMO = 10; // Máximo de conexiones permitidas

    public static void main(String[] args) throws IOException {
        int port = 44444;
        ServerSocket server = new ServerSocket(port);
        System.out.println("Servidor iniciado...");

        Socket[] tabla = new Socket[MAXIMO]; // control clientes
        ComunHilos comun = new ComunHilos(MAXIMO, 0, 0, tabla);

        while (comun.getConexiones() < MAXIMO) {
            Socket socket = new Socket();
            /*String ip = "192.168.0.194";
            socket = new Socket(ip, port);*/
            socket = server.accept(); // esperando clientes

            // Objeto compartido por los hilos
            comun.addTabla(socket, comun.getConexiones());
            comun.setActuales(comun.getActuales() + 1);
            comun.setConexiones(comun.getConexiones() + 1);

            HiloServidorChat hilo = new HiloServidorChat(socket, comun);
            hilo.start();
        }
        server.close();
    }
}
