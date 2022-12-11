package Actividad2.ModeloProductorConsumidor.Version1;

public class Productor extends Thread {
    private Cola cola;
    private int num;

    public Productor(Cola c, int n) {
        this.cola = c;
        this.num = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i);
            System.out.println(i + " => Productor: " + num + ", produce: " + i);

            try {
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
