package ejemplos;

import java.security.Provider;
import java.security.Security;

public class InfoProveedores {
    public static void main(String[] args) {
        System.out.println("------------------------------------");
        System.out.println("Proveedores instalados en el sistema");
        System.out.println("------------------------------------");

        Provider[] listaProv = Security.getProviders();

        for (int i = 0; i < listaProv.length; i++) {
            System.out.println("Núm. proveedor  : " + (i + 1));
            System.out.println("Nombre          : " + listaProv[i].getName());
            System.out.println("Versión         : " + listaProv[i].getVersion());
            System.out.println("Información     : " + listaProv[i].getInfo() + "\n");
        }
    }
}
