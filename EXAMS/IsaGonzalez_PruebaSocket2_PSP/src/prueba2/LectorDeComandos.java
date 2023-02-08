package prueba2;

import java.net.Socket;

public class LectorDeComandos {
    // Constructor vacío requerido
    public LectorDeComandos() {
    }

    // Menú de opciones
    public static void menu() {
        System.out.println("-- MENÚ DE OPCIONES --");
        System.out.println("1. Libro");
        System.out.println("2. Frase");
        System.out.println("3. Exit");
    }

    // Método 'process' al que le entra como parámetro el cliente y el comando que éste ha escrito
    public static String process(Socket cliente, String comando) {

        // Array de libros
        String[] libros = new String[]{
                "Catalina de Lancaster - María Jesús Álvarez",
                "Don Quijote de la Mancha - Miguel de Cervantes y Saavedra",
                "Cien años de soledad - Gabriel García Márquez",
                "El invierno del mundo - Ken Follet",
                "Africanus, el hijo del cónsul - Santiago Posteguillo"};

        // Array de frases
        String[] frases = new String[]{
                "No dejes para mañana lo que puedas hacer hoy - Pepe Grillo",
                "Un título abre puertas, el conocimiento las mantiene abiertas - jlr2",
                "Un lenguaje de programación es de bajo nivel cuando requiere que prestes atención a lo irrelenvante - Alan J. Perlis",
                "Hablar es barato. Enséñame el código - Linus Torvalds"};

        String mensaje;

        // Si el comando escrito por el cliente es 'libro', se devolverá uno de los incluídos en el array de forma aleatoria
        if (comando.equalsIgnoreCase("libro")) {
            mensaje = libros[(int) (Math.random() * 5)];
        }
        // Si el comando escrito por el cliente es 'frase', se devolverá una de las incluídas en el array de forma aleatoria
        else if (comando.equalsIgnoreCase("frase")) {
            mensaje = frases[(int) (Math.random() * 4)];
        }
        // Si el comando escrito por el cliente es 'exit', se devolverá un mensaje diciendo que ese cliente sale
        else if (comando.equalsIgnoreCase("exit")) {
            mensaje = "Cliente " + cliente + " saliendo";
        }
        // Y si escribe cualquier otra cosa que no esté entre las opciones del menú, se devolverá un mensaje diciendo que la petición no se puede resolver
        else {
            mensaje = "La petición no se puede resolver";
        }

        return mensaje;
    }
}
