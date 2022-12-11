package Actividad2.ModeloProductorConsumidor.Actividad2_10;

public class ColaAct {
    private int num;
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
        return this.num;
    }

    public synchronized void put(int valor) {
        while (disponible == true) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.num = valor;
        this.disponible =true;
        notifyAll();
    }
}
