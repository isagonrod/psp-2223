package ejemplos;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Ejemplo11 {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();

            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(Cipher.ENCRYPT_MODE, clavepub);

            byte[] textoPlano = "Esto es un texto plano".getBytes();
            byte[] textoCifrado = c.doFinal(textoPlano);

            c.init(Cipher.DECRYPT_MODE, clavepriv);
            byte[] desencriptado = c.doFinal(textoCifrado);

            System.out.println("Encriptado: " + new String(textoCifrado));
            System.out.println("Desencriptado: " + new String(desencriptado));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
