package ejemplos;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class AlmacenarClaveSecreta {
    public static void main(String[] args) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);

            // Genera clave secreta
            SecretKey clave = kg.generateKey();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Clave secreta"));
            out.writeObject(clave);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
