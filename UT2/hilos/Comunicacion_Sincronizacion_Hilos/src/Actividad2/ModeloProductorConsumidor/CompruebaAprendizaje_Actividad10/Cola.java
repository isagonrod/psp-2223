package Actividad2.ModeloProductorConsumidor.CompruebaAprendizaje_Actividad10;

public class Cola {
    private int numero;
    private boolean disponible = false; // inicialmente cola vacía

    public synchronized int get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        disponible = false;
        notifyAll();
        return numero;
    }

    public synchronized void put(int valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        numero = valor;
        disponible = true;
        notifyAll();
    }
}
