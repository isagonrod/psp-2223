package ejemplo1;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ServidorSSL {
    public static void main(String[] args) throws Exception {
        int port = 6000;

        System.setProperty("javax.net.ssl.keyStore", "AlmacenSrv");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

        // Crear un socket servidor seguro
        SSLServerSocketFactory serverSocket = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket server = (SSLServerSocket) serverSocket.createServerSocket(port);

        SSLSocket clientConnected;
        DataInputStream inputStream; // Flujo de entrada del cliente
        DataOutputStream outputStream; // Flujo de salida al cliente

        for (int i = 0; 1 < 5; i++) {
            System.out.println("Esperando al cliente " + i);
            clientConnected = (SSLSocket) server.accept();

            // El cliente me envía un mensaje
            inputStream = new DataInputStream(clientConnected.getInputStream());
            System.out.println("Recibiendo del CLIENTE: " + i + "\n\t" + inputStream.readUTF());

            // Envío un saludo al cliente
            outputStream = new DataOutputStream(clientConnected.getOutputStream());
            outputStream.writeUTF("Saludos al cliente del servidor");
        }

        // Cerrar Streams y Sockets
        inputStream.close();
        outputStream.close();
        clientConnected.close();
        server.close();
    }
}
