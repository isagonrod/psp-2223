package actividades;

/* ACTIVIDAD 2.1
 * Crea dos clases (hilos) Java que extiendan la clase Thread.
 * Uno de los hilos debe visualizar en pantalla en un bucle infinito la palabra TIC y el otro hilo la palabra TAC.
 * Dentro del bucle utiliza el método sleep() para que nos dé tiempo a ver las palabras que se visualizan cuando lo
 * ejecutemos, tendrás que añadir un bloque try-catch (para capturar la excepción InterruptedException).
 * Crea después la función main() que haga uso de los hilos anteriores.
 * ¿Se visualizan los textos TIC y TAC de forma ordenada (es decir, TIC TAC TIC TAC...)?
 */

public class TicTacClass extends Thread {
    public TicTacClass(String name) {
        super(name);
    }

    public void run() {
        try {
            while(true) {
                System.out.print(getName() + " ");
                sleep(2000L);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        TicTacClass tic = new TicTacClass("TIC");
        TicTacClass tac = new TicTacClass("TAC");

        tic.start();
        tac.start();
    }
}
