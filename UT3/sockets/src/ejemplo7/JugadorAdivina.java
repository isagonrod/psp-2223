package ejemplo7;

import util.Teclado;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class JugadorAdivina {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String host = "localhost";
        int port = 6001; // puerto remoto
        Socket cliente = new Socket(host, port);

        ObjectOutputStream fsalida = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream fentrada = new ObjectInputStream(cliente.getInputStream());

        // Flujo para entrada estándar
        String cadena = "";

        // Obtener primer objeto enviado por el servidor
        Datos datos = (Datos) fentrada.readObject();
        int id = datos.getIdentificador();
        System.out.println("Id jugador: " + id);
        System.out.println(datos.getCadena());

        if (!datos.isJuega()) {
            cadena = "*";
        }

        while (datos.isJuega() && !cadena.trim().equals("*")) {
            System.out.println("Intento: " + (datos.getIntentos() + 1));
            cadena = Teclado.getString("Introduce número: ");
            Datos d = new Datos();

            if (!validarCadena(cadena)) { // Comprobar si es número
                continue;
            }

            d.setCadena(cadena);

            // Enviar datos al servidor, el número leído por teclado
            fsalida.reset();
            fsalida.writeObject(d);

            // Recibir datos del servidor
            datos = (Datos) fentrada.readObject();
            System.out.println(datos.getCadena());

            if (datos.getIntentos() >= 5) {
                System.out.println("<<JUEGO FINALIZADO, NO HAY MÁS INTENTOS>>");
                cadena = "*";
            }

            if (datos.isGana()) {
                System.out.println("<<HAS GANADO!! EL JUEGO HA TERMINADO>>");
                cadena = "*";
            } else {
                if (!datos.isJuega()) {
                    System.out.println("<<EL JUEGO HA TERMINADO, HAN ADIVINADO EL NÚMERO>>");
                    cadena = "*";
                }
            }

            fsalida.close();
            fentrada.close();
            System.out.println("Fin de proceso...");
            cliente.close();
        }
    }

    private static boolean validarCadena(String cadena) {
        // Comprueba si la cadena es numérica
        boolean valor = false;

        try {
            Integer.parseInt(cadena);
            valor = true;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return valor;
    }
}
