package ejercicio3;

/* Comprueba tu aprendizaje - Ejercicio 3
 * Implemente un programa que reciba a través de sus argumentos una lista de ficheros de texto y cuente el número
 * de caracteres que hay en cada fichero.
 * Modifica el programa para que se cree un hilo por cada fichero a contar.
 * Muestra lo que se tarda en contar cada fichero en la primera tarea secuencial y usando hilos.
 * Para calcular el tiempo que tarda en ejecutarse un proceso podemos usar el método System.currentTimeMillis() de la
 * siguiente manera:
 *      long t_comienzo, t_fin;
 *      t_comienzo = System.currentTimeMillis();
 *      Proceso();
 *      t_fin = System.currentTimeMillis();
 *      long tiempototal = t_fin - t_comienzo;
 *      System.out.println("El proceso ha tardado: " + tiempototal + " milisegundos.");
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileCount implements Runnable {
    private String filePath;

    public FileCount(String filePath) {
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
            String line = br.readLine();
            int countLetters = 0;
            while (br.readLine() != null) {
                countLetters = line.replace(" ", "").length();
            }
            System.out.println("Número de caracteres: " + countLetters);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            new Thread(new FileCount(arg)).start();
        }
    }
}
