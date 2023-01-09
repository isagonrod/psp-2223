package Actividad2.ModeloProductorConsumidor.Actividad2_10_PINGPONG;

public class ColaPing {
    private String pinpon;
    private boolean disponible = false;

    public synchronized String get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        disponible = false;
        notifyAll();
        return pinpon;
    }

    public synchronized void put(String valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        pinpon = valor;
        disponible = true;
        notifyAll();
    }
}
