package ejemplos;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

public class Ejemplo10 {
    public static void main(String[] args) {
        try {
            System.out.println("\nENCRIPTADO CON ALGORITMO 'AES'");

            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey claveSecreta = kg.generateKey();

            Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, claveSecreta);

            byte[] textoPlano = "Esto es un texto plano".getBytes();
            byte[] textoCifrado = c.doFinal(textoPlano);
            System.out.println("Encriptado: " + new String(textoCifrado));

            c.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte[] desencriptado = c.doFinal(textoCifrado);
            System.out.println("Desencriptado: " + new String(desencriptado));

//            System.out.println("\nENCRIPTADO CON ALGORITMO 'DES'");
//
//            KeyGenerator keyG = KeyGenerator.getInstance("DES");
//            Cipher cp = Cipher.getInstance("DES/CBC/PKCS5Padding");
//            Key clave = keyG.generateKey();
//
//            // Devuelve el vector IV inicializado en un nuevo buffer
//            byte[] iv = cp.getIV();
//            IvParameterSpec dps = new IvParameterSpec(iv);
//            cp.init(Cipher.DECRYPT_MODE, clave, dps);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
