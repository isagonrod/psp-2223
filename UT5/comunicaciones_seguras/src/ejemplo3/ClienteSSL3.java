package ejemplo3;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class ClienteSSL3 {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO...");

        // Definir el fichero almacén que contiene el certificado de confianza y la clave para acceder a él
        FileInputStream ficCerConf = new FileInputStream("CliCertConfianza");
        String claveCerConf = "890123";

        // Cargar en un KeyStore el almacén de certificados de confianza
        KeyStore almacenConf = KeyStore.getInstance(KeyStore.getDefaultType());
        almacenConf.load(ficCerConf, claveCerConf.toCharArray());

        // Crear el gestor de confianza a partir del objeto KeyStore e inicializarlo con la clave del almacén
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(almacenConf);

        // Creación del contexto con soporte TLS
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        // Creación del socket SSL de cliente a partir del contexto
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        SSLSocket client = (SSLSocket) socketFactory.createSocket(host, port);

        // Creo flujo de salida al servidor
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());

        // Envío un saludo al servidor
        outputStream.writeUTF("Saludos al SERVIDOR desde el CLIENTE");

        // Creo un flujo de entrada al servidor
        DataInputStream inputStream = new DataInputStream(client.getInputStream());

        // El servidor me envía un mensaje
        System.out.println("Recibiendo del SERVIDOR: \n\t" + inputStream.readUTF());

        // Información SSL
        SSLSession session = client.getSession();
        X509Certificate certificate = (X509Certificate) session.getPeerCertificates()[0];
        System.out.println(
                        "Host                   : " + session.getPeerHost() + "\n" +
                        "Cifrado                : " + session.getCipherSuite() + "\n" +
                        "Protocolo              : " + session.getProtocol() + "\n" +
                        "IDentificador          : " + new BigInteger(session.getId()) + "\n" +
                        "Creación de la sesión  : " + session.getCreationTime() + "\n" +
                        "Propietario            : " + certificate.getSubjectDN() + "\n" +
                        "Algoritmo              : " + certificate.getSigAlgName() + "\n" +
                        "Tipo                   : " + certificate.getType() + "\n" +
                        "Emisor                 : " + certificate.getIssuerDN() + "\n" +
                        "Número de serie        : " + certificate.getSerialNumber()
        );


        // Cerrar Streams y Sockets
        inputStream.close();
        outputStream.close();
        client.close();
    }
}
