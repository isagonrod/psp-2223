package Actividad2.ModeloProductorConsumidor.Actividad2_10;

public class ProductorAct extends Thread {
    private ColaAct cola;
    private int num;

    public ProductorAct(ColaAct c, int n) {
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
