package actividades;

/* ACTIVIDAD 5.1
 * Prueba el ejercicio anterior con el algoritmo MD5.
 * Comprueba el número de bytes generados.
 *
 * Leer por teclado tres cadenas, una de ellas actuará como clave.
 * Calcular el mensaje resumen cifrado de dos de las cadenas con la otra que actúa como clave,
 * comprobar si los resúmenes son iguales.
 * Usar el algoritmo SHA-256.
 */

import util.Teclado;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class act_5_1 {
    public static void main(String[] args) {
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            //messageDigest = MessageDigest.getInstance("SHA-256");
            String texto1 = Teclado.getString("Introduce cadena 1: ");
            String texto2 = Teclado.getString("Introduce cadena 2: ");
            String clave = Teclado.getString("Introduce clave: ");

            byte[] dataBytes1 = texto1.getBytes(); // texto1 a bytes
            messageDigest.update(dataBytes1); // se introduce texto1 en bytes a resumir
            byte[] resumen1 = messageDigest.digest(clave.getBytes()); // se calcula el resumen

            byte[] dataBytes2 = texto2.getBytes(); // texto2 a bytes
            messageDigest.update(dataBytes2); // se introduce texto2 en bytes a resumir
            byte[] resumen2 = messageDigest.digest(clave.getBytes()); // se calcula el resumen

            System.out.println("Resumen TEXTO 1\n\t" + new String(resumen1));
            System.out.println("Resumen TEXTO 2\n\t" + new String(resumen2));

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
}
