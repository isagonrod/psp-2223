package ejemplos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ejemplo5 {
    public static void main(String[] args) {
        try {
            FileOutputStream fileout = new FileOutputStream("DATOS.DAT");
            ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            String datos = "En un lugar de la Mancha, " +
                    "de cuyo nombre no quiero acordarme, " +
                    "no ha mucho tiempo que vivía un hidalgo " +
                    "de los de lanza en astillero, adarga antigua, " +
                    "rocín flaco y galgo corredor.";
            byte[] dataBytes = datos.getBytes();

            messageDigest.update(dataBytes); // TEXTO A RESUMIR
            byte[] resumen = messageDigest.digest(); // SE CALCULA EL RESUMEN
            dataOS.writeObject(datos); // se escriben los datos
            dataOS.writeObject(resumen); // se escribe el resumen

            dataOS.close();
            fileout.close();
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
}
