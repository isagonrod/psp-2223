package Actividad1.CompartirInf3;

public class ContadorSincronizado {
    private int c = 0;

    public synchronized void incrementa() {
        c++;
    }

    public synchronized void decrementa() {
        c--;
    }

    public synchronized int valor() {
        return c;
    }
}
