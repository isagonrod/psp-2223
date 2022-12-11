package Actividad2.ModeloProductorConsumidor.Version2;

public class ProductorSincronizado extends Thread {
    private ColaSincronizada cola;
    private int num;

    public ProductorSincronizado(ColaSincronizada c, int n) {
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
