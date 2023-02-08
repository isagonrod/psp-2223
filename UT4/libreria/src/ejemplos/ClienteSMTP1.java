package ejemplos;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

import java.io.IOException;

public class ClienteSMTP1 {
    public static void main(String[] args) {
        SMTPClient client = new SMTPClient();

        try {
            int respuesta;

            // Nos conectamos al puerto 25
            client.connect("localhost", 25);
            System.out.println(client.getReplyString());
            respuesta = client.getReplyCode();

            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA.");
                System.exit(1);
            }

            // Realizar acciones, por ejemplo enviar un correo
            client.login(); // inicio de sesión HELO
            String destinatario = "isabelmariagonzalezrodriguez@gmail.com";
            String mensaje = "Hola. \nEnviando saludos. \n Ciao";
            String remitente = "yo@localhost.es";

            if (client.sendSimpleMessage(remitente, destinatario, mensaje)) {
                System.out.println("Mensaje enviado a " + destinatario);
            } else {
                System.out.println("No se ha podido enviar.");
            }

            client.logout(); // final de sesión QUIT

            // Nos desconectamos
            client.disconnect();

        } catch (IOException e) {
            System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
            e.printStackTrace();
            System.exit(2);
        }
    }
}
