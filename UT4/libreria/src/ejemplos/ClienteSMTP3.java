package ejemplos;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

public class ClienteSMTP3 {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException,
            KeyStoreException, InvalidKeyException, InvalidKeySpecException {

        // se crea cliente SMTP seguro
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        // datos del usuario y del servidor
        String server = "smtp.gmail.com";
        String username = "isabelmariagonzalezrodriguez@gmail.com";
        String password = "MAirena_1985";
        int puerto = 587;
        String remitente = "isabelmariagonzalezrodriguez@gmail.com";

        try {
            int respuesta;

            // creación de la clave para establecer un canal seguro
            KeyManagerFactory key = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            key.init(null, null);
            KeyManager keyManager = key.getKeyManagers()[0];

            // nos conectamos al servidor SMTP
            client.connect(server, puerto);
            System.out.println("1 - " + client.getReplyString());

            // se establece la clave para la comunicación segura
            client.setKeyManager(keyManager);

            respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA.");
                System.exit(1);
            }

            // se envía el comando EHLO
            client.ehlo(server); // necesario
            System.out.println("2 - " + client.getReplyString());

            // NECESITA NEGOCIACIÓN TLS - MODO NO IMPLÍCITO
            // Se ejecuta el comando STARTTLS y se comprueba si es true
            if (client.execTLS()) {
                System.out.println("3 - " + client.getReplyString());

                // se realiza la autenticación con el servidor
                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
                    System.out.println("4 - " + client.getReplyString());
                    String destino1 = "isabelmaria.gonzalezrodriguez@iesvalleinclan.es";
                    String asunto = "Prueba de SMTPClient con GMAIL";
                    String mensaje = "Hola.\nEnviando saludos.\nUsando GMAIL.\nCIAO";

                    // se crea la cabecera
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, asunto);

                    // el nombre de usuario y el email de origen coinciden
                    client.setSender(remitente);
                    client.addRecipient(destino1);
                    System.out.println("5 - " + client.getReplyString());

                    // se envía DATA
                    Writer writer = client.sendMessageData();
                    if (writer == null) { // fallo
                        System.out.println("FALLO AL ENVIAR DATA.");
                        System.exit(1);
                    }

                    writer.write(cabecera.toString()); // cabecera
                    writer.write(mensaje); // luego mensaje
                    writer.close();
                    System.out.println("6 - " + client.getReplyString());

                    boolean exito = client.completePendingCommand();
                    System.out.println("7 - " + client.getReplyString());

                    if (!exito) {
                        System.out.println("FALLO AL FINALIZAR TRANSACCIÓN.");
                        System.exit(1);
                    } else {
                        System.out.println("MENSAJE ENVIADO CON ÉXITO...");
                    }
                } else {
                    System.out.println("USUARIO NO AUTENTICADO.");
                }
            } else {
                System.out.println("FALLO AL ESCUCHAR STARTTLS");
            }
        } catch (IOException e) {
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Fin de envío.");
        System.exit(0);
    }
}
