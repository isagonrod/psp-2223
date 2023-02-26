package ejemplo2;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClienteSSL2 {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 6000;

        System.setProperty("javax.net.ssl.trustStore", "CliCertConfianza");
        System.setProperty("javax.net.ssl.trustStorePassword", "890123");
        System.setProperty("javax.net.ssl.keyStore", "D:/2022-2023_DAM/PSP/Repo_GitHub/psp-2223/UT5/comunicaciones_seguras/src");

        System.out.println("PROGRAMA CLIENTE INICIADO...");

        // Crear un socket cliente seguro
        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket client = (SSLSocket) socketFactory.createSocket(host, port);

        // Creo flujo de salida al servidor
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());

        // Envío un saludo al servidor
        outputStream.writeUTF("Saludos al SERVIDOR desde el CLIENTE");

        // Creo un flujo de entrada al servidor
        DataInputStream inputStream = new DataInputStream(client.getInputStream());

        // El servidor me envía un mensaje
        System.out.println("Recibiendo del SERVIDOR: \n\t" + inputStream.readUTF());

        // Cerrar Streams y Sockets
        inputStream.close();
        outputStream.close();
        client.close();
    }
}
