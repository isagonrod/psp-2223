package actividades;

/* ACTIVIDAD 5.3
 * Almacena las claves pública y privada en disco en los ficheros Clave.publica y Clave.privada respectivamente.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class act_5_3 {
    public static void main(String[] args) {

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

            // SE INICIALIZA EL GENERADOR DE CLAVES
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(2048, numero);

            // SE CREA EL PAR DE CLAVES PRIVADA Y PÚBLICA
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavePrivada = par.getPrivate();
            PublicKey clavePublica = par.getPublic();

            // FIRMA CON CLAVE PRIVADA EL MENSAJE
            // AL OBJETO Signature SE LE SUMINISTRAN LOS DATOS A FIRMAR
            Signature dsa = Signature.getInstance("SHA256withDSA");
            dsa.initSign(clavePrivada);
            String mensaje = "Este mensaje va a ser firmado";
            dsa.update(mensaje.getBytes());

            byte[] firma = dsa.sign(); // MENSAJE FIRMADO

            // EL RECEPTOR DEL MENSAJE
            // VERIFICA CON LA CLAVE PÚBLICA EL MENSAJE FIRMADO
            // AL OBJETO Signature SE LE SUMINISTRAN LOS DATOS A VERIFICAR
            Signature verficadsa = Signature.getInstance("SHA256withDSA");
            verficadsa.initVerify(clavePublica);
            verficadsa.update(mensaje.getBytes());
            boolean check = verficadsa.verify(firma);

            if (check) {
                System.out.println("FIRMA VERIFICADA CON CLAVE PÚBLICA");
            } else {
                System.out.println("FIRMA NO VERIFICADA");
            }

            PKCS8EncodedKeySpec pkcs8Spec = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());

            // Escribir a fichero binario la clave privada
            FileOutputStream outpriv = new FileOutputStream("Clave.privada");
            outpriv.write(pkcs8Spec.getEncoded());
            outpriv.close();

            X509EncodedKeySpec pkX509 = new X509EncodedKeySpec(clavePublica.getEncoded());

            // Escribir a fichero binario la clave pública
            FileOutputStream outpub = new FileOutputStream("Clave.publica");
            outpub.write(pkX509.getEncoded());
            outpub.close();

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
