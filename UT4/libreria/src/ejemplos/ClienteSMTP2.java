package ejemplos;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.IOException;
import java.io.Writer;

public class ClienteSMTP2 {
    public static void main(String[] args) {
        SMTPClient client = new SMTPClient();

        try {
            int answer;

            // Nos conectamos al puerto 25
            client.connect("localhost");
            System.out.println(client.getReplyString());
            answer = client.getReplyCode();

            if (!SMTPReply.isPositiveCompletion(answer)) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA.");
                System.exit(1);
            }

            client.login(); // inicia sesión
            String remitente = "yo@localhost.es";
            String destino1 = "alumnouni5@gmail.com";
            String destino2 = "isabelmariagonzalezrodriguez@gmail.com";
            String asunto = "Prueba de SMTPClient";
            String mensaje = "Hola. \nEnviando saludos. \n Bye.";

            // se crea la cabecera
            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, asunto);
            cabecera.addCC(destino2);

            // establecer el correo de origen
            client.setSender(remitente);

            // añadir correos de destino
            client.addRecipient(destino1);
            client.addRecipient(destino2);

            // se envía DATA
            Writer writer = client.sendMessageData();
            if (writer == null) { // fallo
                System.out.println("FALLO AL ENVIAR DATA.");
                System.exit(1);
            }

            // pintamos cabecera
            System.out.println(cabecera);

            writer.write(cabecera.toString());
            writer.write(mensaje);
            writer.close();

            if (client.completePendingCommand()) { // fallo
                System.out.println("FALLO AL FINALIZAR LA TRANSACCIÓN");
                System.exit(1);
            }

            client.logout(); // finaliza sesión

        } catch (IOException e) {
            System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
            e.printStackTrace();
            System.exit(2);
        }

    }
}
