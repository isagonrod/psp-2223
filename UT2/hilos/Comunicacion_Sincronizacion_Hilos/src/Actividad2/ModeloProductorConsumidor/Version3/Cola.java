package Actividad2.ModeloProductorConsumidor.Version3;

public class Cola {
    private int num;
    private boolean disponible = false;
    private int turno = 1;

    public synchronized int get(int i, int n) {
        while (!disponible || n != turno) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            disponible = false;

            if (turno == 1) {
                turno = 2;
            } else {
                turno = 1;
            }

        }
        notifyAll();
        System.out.println(i + " => Consumidor " + n + ": consume " + num);
        return num;
    }

    public synchronized void put(int valor, int n) {
        while (disponible || n != turno) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        num = valor;
        System.out.println(valor + " => Productor " + n + ": produce " + valor);
        disponible = true;
        notifyAll();
    }
}
