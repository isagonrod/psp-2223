package Actividad2.ModeloProductorConsumidor.Actividad2_10;

public class ConsumidorAct extends Thread {
    private ColaAct cola;
    private int num;

    public ConsumidorAct(ColaAct c, int n) {
        this.cola = c;
        this.num = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cola.get();
            System.out.println(i + " => Consumidor: " + num + ", consume: " + valor);
        }
    }
}
