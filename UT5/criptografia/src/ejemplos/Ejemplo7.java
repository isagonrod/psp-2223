package ejemplos;

import java.security.*;

public class Ejemplo7 {
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
        }
    }
}
