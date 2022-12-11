package Actividad2.ModeloProductorConsumidor.Version1;

public class Cola {
    private int numero;
    private boolean disponible = false;

    public synchronized int get() {
        if (disponible) {
            this.disponible = false;
            return this.numero;
        }
        return -1;
    }

    public synchronized void put(int valor) {
        if (disponible == false) {
            this.numero = valor;
            this.disponible = true;
        }
        System.out.println("Cola: " + this.numero);
    }
}
