package ejemplos;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;

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
