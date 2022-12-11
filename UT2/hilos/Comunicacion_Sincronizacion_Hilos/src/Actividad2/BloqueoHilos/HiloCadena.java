package Actividad2.BloqueoHilos;

public class HiloCadena extends Thread {
    private final ObjetoCompartido objeto;
    String cadena;

    public HiloCadena(ObjetoCompartido c, String s) {
        this.objeto = c;
        this.cadena = s;
    }

    public void run() {
        synchronized (objeto) {
            for (int i = 0; i < 10; i++) {
                objeto.PintaCadena(cadena);
                objeto.notify();

                try {
                    objeto.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            objeto.notify();
        }
        System.out.print("\n" + cadena + " finalizado");
    }
}
