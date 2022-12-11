package Actividad2.ModeloProductorConsumidor.Version2;

public class ColaSincronizada {
    private int numero;
    private boolean disponible = false;

    public synchronized int get() {
        while (disponible == false) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.disponible = false;
        notifyAll();
        return this.numero;
    }

    public synchronized void put(int valor) {
        while (disponible == true) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.numero = valor;
        this.disponible =true;
        notifyAll();
    }
}
