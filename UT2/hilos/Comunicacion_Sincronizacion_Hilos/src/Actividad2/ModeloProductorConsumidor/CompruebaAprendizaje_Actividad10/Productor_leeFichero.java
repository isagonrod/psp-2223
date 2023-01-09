package Actividad2.ModeloProductorConsumidor.CompruebaAprendizaje_Actividad10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Productor_leeFichero  extends Thread {
    private Cola cola;
    private String nombre;

    public Productor_leeFichero(Cola c, String nombreFichero) {
        this.cola = c;
        this.nombre = nombreFichero;
    }

    public void run() {
        File fichero = new File(nombre); // declarar fichero
        if (fichero.exists()) {
            try {
                FileReader fich = new FileReader(fichero);
                int i;
                while ((i = fich.read()) != -1) {
                    char c = (char) i;
                    cola.put(c);
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                cola.put(i);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("\nFin Productor");
    }
}
