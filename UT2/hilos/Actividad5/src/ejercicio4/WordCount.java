package ejercicio4;

/* Comprueba tu aprendizaje - Ejercicio 4
 * Haz un programa Java que reciba a través de sus argumentos una lista de ficheros de texto y cuente el número de palabras
 * que hay en cada fichero.
 * Se debe crear un hilo por cada fichero a contar.
 * Muestra el número de palabras de cada fichero y lo que tarda en contar las palabras.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount implements Runnable {
    private String filePath;

    public WordCount(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        long t_comienzo, t_fin;
        t_comienzo = System.currentTimeMillis();
        readFile(this.filePath);
        t_fin = System.currentTimeMillis();
        long totalTime = t_fin - t_comienzo;
        System.out.println("El proceso ha tardado: " + totalTime + " milisegundos.");
    }

    public void readFile(String filePath) {
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringTokenizer st;
            String line = br.readLine();
            int countWords = 0;
            while (line != null) {
                st = new StringTokenizer(line," ");
                countWords += st.countTokens();
                line = br.readLine();
            }
            System.out.println("Número de palabras: " + countWords);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            new Thread(new WordCount(arg)).start();
        }
    }
}
