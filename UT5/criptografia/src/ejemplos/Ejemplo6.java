package ejemplos;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

/* ACTIVIDAD 5.2
 * Ejecuta el 'Ejemplo5' para generar el fichero DATOS.DAT
 * Después ejecuta el 'Ejemplo6'.
 *
 * Edita el fichero DATOS.DAT y cambia alguna letra, por ejemplo, donde hay una minúscula pon una mayúscula
 * y prueba de nuevo el 'Ejemplo6' para ver la salida.
 */

public class Ejemplo6 {
    public static void main(String[] args) {
        try {
            FileInputStream fileout = new FileInputStream("DATOS.DAT");
            ObjectInputStream dataOS = new ObjectInputStream(fileout);
            Object obj = dataOS.readObject();

            // Primera lectura
            String datos = (String) obj;
            System.out.println("Datos: " + datos);

            // Segunda lectura, se obtiene el resumen
            obj = dataOS.readObject();
            byte[] resumenOriginal = (byte[]) obj;

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Se calcula el resumen del String leído del fichero
            messageDigest.update(datos.getBytes()); // TEXTO A RESUMIR
            byte[] resumenActual = messageDigest.digest(); // SE CALCULA EL RESUMEN

            // Se comprueban los dos resúmenes
            if (MessageDigest.isEqual(resumenActual, resumenOriginal)) {
                System.out.println("DATOS VÁLIDOS");
            } else {
                System.out.println("DATOS NO VÁLIDOS");
            }

            dataOS.close();
            fileout.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
