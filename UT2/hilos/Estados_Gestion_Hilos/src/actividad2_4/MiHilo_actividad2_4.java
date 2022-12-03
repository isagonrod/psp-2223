package actividad2_4;

/* SUSPENDER HILOS
 * Crear un hilo (extends Thread) que pinte el valor de un contador en un bucle controlado por una variable que finalice el bucle.
 * Dentro del bucle, además de pintar el valor del contador, ponéis a dormir (sleep() para ver los números) el hilo y
 * llamáis al método que comprueba si el hilo está suspendido o no.
 *
 * Al finalizar el bucle, visualizar mensaje de fin de hilo.
 *
 * En main, introducir cadenas por teclado hasta escribir un *.
 * - Si la cadena es S suspender el hilo.
 * - Si la cadena es R reanudar el hilo.
 * El hilo no se lanzará después de introducir la cadena por teclado, solo una vez...
 * Al finalizar el bucle, visualizar valor del contador, debe finalizar el hilo.
 */

public class MiHilo_actividad2_4 extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();
    private boolean stopHilo = false;
    private int c = 0;

    public void pararHilo() {
        stopHilo = true;
    }

    int getContador() {
        return c;
    }

    public void Suspende() {
        suspender.set(true);
    }

    public void Reanuda() {
        suspender.set(false);
    }

    public void run() {
        try {
            while(!stopHilo) {
                c++;
                System.out.println(" " + c + " ");
                // sleep(200);
                suspender.esperandoParaReanudar();
                sleep(200);
            }
            System.out.println("Fin del hilo");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
