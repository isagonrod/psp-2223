package Actividad2.ModeloProductorConsumidor.Version3;

public class Productor extends Thread {
    private Cola cola;
    private int num;

    public Productor (Cola c, int n) {
        this.cola = c;
        this.num = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i, num);
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
