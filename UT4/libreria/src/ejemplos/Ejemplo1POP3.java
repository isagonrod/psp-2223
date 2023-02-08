package ejemplos;

import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;

import java.io.IOException;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

public class Ejemplo1POP3 {
    public static void main(String[] args) {
        String server = "localhost", username = "usu1", password = "usu1";
        int puerto = 110;

        POP3Client pop3 = new POP3Client();
        try {
            // nos conectamos al servidor
            pop3.connect(server, puerto);
            System.out.println("Conexión realizada al servidor POP3 " + server);

            // iniciamos sesión
            if (!pop3.login(username, password)) {
                System.err.println("Error al hacer login");
            } else {
                // obtenemos todos los mensajes en un array
                POP3MessageInfo[] men = pop3.listMessages();

                if (messages == null) {
                    System.out.println("Imposible recuperar mensajes.");
                } else {
                    System.out.println("Nº de mensajes: " + men.length);
                }

                recuperarMensajes(men, pop3);

                // nos deconectamos
                pop3.logout();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void recuperarMensajes(POP3MessageInfo[] men, POP3Client pop3) throws IOException {
        for (int i = 0; i < men.length; i++) {
            System.out.println("Mensaje: " + (i + 1));
            POP3MessageInfo msginfo = men[i]; // lista de mensajes
            System.out.println("Identificador: " + msginfo.identifier + ", Number: " + msginfo.number + ", Tamaño: " + msginfo.size);

            System.out.println("Prueba de listUniqueIdentifier: ");
            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1); // un mensaje
            System.out.println("\tIdentificador: " + pmi.identifier + ", Number: " + pmi.number + ", Tamaño: " + pmi.size);
        }
    }
}
