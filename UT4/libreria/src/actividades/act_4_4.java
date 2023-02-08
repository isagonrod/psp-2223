package actividades;

/* ACTIVIDAD 4.4
 * Realiza un programa Java que permita a cualquiera que lo ejecute enviar un correo electrónico.
 * Todos los datos que se necesitan se introducirán por teclado: el servidor SMTP, si necesita negociación TLS, el puerto,
 * nombre de usuario, su password, el correo del remitente (no siempre coincide con el nombre de usuario) y destinatario,
 * asunto y el mensaje, que permitirá varias líneas y cuando se introduzca un * finalizará el texto del mensaje:
 *
 *  Introduce servidor SMTP..........:
 *  Necesita negociación TLS (S/N)...:
 *  Introduce usuario................:
 *  Introduce password...............:
 *  Introduce puerto.................:
 *  Introduce correo del remitente...:
 *  Introduce correo destinatario....:
 *  Introduce asunto.................:
 *  Introduce mensaje, finalizará cuando se introduzca un *:
 *
 * Se deben mostrar los mensajes de cada acción con el servidor SMTP.
 * Se debe mostrar un mensaje indicando si la negociación se ha establecido o no.
 * No se puede enviar un mensaje vacío.
 * Mostrar más mensajes que consideréis necesarios para aclarar la situación.
 */

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
import util.Teclado;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;

public class act_4_4 {
    public static void main(String[] args) throws Exception {

        // se crea cliente SMTP seguro
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        // datos del usuario y del servidor
        String server = Teclado.getString("Introduce servidor SMTP: ");
        boolean negTLS = Boolean.parseBoolean(Teclado.getString("Necesita negociación TLS (S/N): "));
        String username = Teclado.getString("Introduce usuario: ");
        String password = Teclado.getString("Introduce password: ");
        int port = Teclado.getNumber("Introduce puerto: ");
        String emailRemit = Teclado.getString("Introduce correo del remitente: ");
        String emailDestin = Teclado.getString("Introduce correo destinatario: ");
        String asunto = Teclado.getString("Introduce asunto: ");
        String message = Teclado.getLongString("Introduce mensaje, finalizará cuando se introduzca *: ");

        try {
            int respuesta;

            // creación de la clave para establecer un canal seguro
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(null, null);
            KeyManager keyManager = keyManagerFactory.getKeyManagers()[0];

            // nos conectamos al servidor SMTP
            client.connect(server, port);
            System.out.println("1 - " + client.getReplyString());

            // se establece la clave para la comunicación segura
            client.setKeyManager(keyManager);

            respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA");
                System.exit(1);
            }

            // se envía al comando EHLO
            client.ehlo(server); // necesario
            System.out.println("2 - " + client.getReplyString());

            // NECESITA NEGACIÓN TLS - MODO NO IMPLÍCITO
            // Se ejecuta el comando STARTTLS y se comprueba si es true
            if (client.execTLS() && negTLS) {
                System.out.println("3 - " + client.getReplyString());

                // se realiza la autenticación con el servidor
                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
                    System.out.println("4 - " + client.getReplyString());

                    // se crea la cabecera
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(emailRemit, emailDestin, asunto);

                    // el nombre de usuario y el email de origen coinciden
                    client.setSender(emailRemit);
                    client.addRecipient(emailDestin);
                    System.out.println("5 - " + client.getReplyString());

                    // se envía DATA
                    Writer writer = client.sendMessageData();
                    if (writer == null) {
                        System.out.println("FALLO AL ENVIAR DATA");
                        System.exit(1);
                    }

                    writer.write(cabecera.toString());
                    writer.write(message);
                    writer.close();
                    System.out.println("6 - " + client.getReplyString());

                    boolean exito = client.completePendingCommand();
                    System.out.println("7 - " + client.getReplyString());

                    if (!exito) {
                        System.out.println("FALLO AL FINALIZAR TRANSACCIÓN");
                        System.exit(1);
                    } else {
                        System.out.println("MENSAJE ENVIADO CON ÉXITO...");
                    }
                } else {
                    System.out.println("USUARIO NO AUTENTICADO");
                }
            } else {
                System.out.println("FALLO AL ESCUCHAR STARTTLS");
            }
        } catch (IOException ex) {
            System.err.println("Could not connect to server");
            ex.printStackTrace();
            System.exit(1);
        }

        try {
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Fin de envío");
        System.exit(0);
    }
}
