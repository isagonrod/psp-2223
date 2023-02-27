package ejemplo3;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.security.KeyStore;

public class ServidorSSL3 {
    public static void main(String[] args) throws Exception {
        int port = 6000;

        // Definir el fichero almacén que contiene el certificado y la clave para acceder a él
        FileInputStream ficAlmacen = new FileInputStream("AlmacenSrv");
        String claveAlmacen = "1234567";

        // Cargar en un KeyStore el almacén que contiene el certificado
        KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
        almacen.load(ficAlmacen, claveAlmacen.toCharArray());

        // Crear el gestor de claves a partir del objeto KeyStore e inicializarlo con la clave del almacén
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(almacen, claveAlmacen.toCharArray());

        // Creación del contexto con soporte TLS
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

        // Creación del socket SSL de servidor a partir del contexto
        SSLServerSocketFactory serverSocket = sslContext.getServerSocketFactory();
        SSLServerSocket sslServer = (SSLServerSocket) serverSocket.createServerSocket(port);

        SSLSocket clientConnected = null;
        DataInputStream inputStream = null; // Flujo de entrada del cliente
        DataOutputStream outputStream = null; // Flujo de salida al cliente

        for (int i = 0; i < 5; i++) {
            System.out.println("Esperando al cliente " + i);
            clientConnected = (SSLSocket) sslServer.accept();

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
        sslServer.close();
    }
}
