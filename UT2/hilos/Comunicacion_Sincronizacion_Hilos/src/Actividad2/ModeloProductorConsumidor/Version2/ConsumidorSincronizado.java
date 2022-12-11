package Actividad2.ModeloProductorConsumidor.Version2;

public class ConsumidorSincronizado extends Thread {
    private ColaSincronizada cola;
    private int num;

    public ConsumidorSincronizado(ColaSincronizada c, int n) {
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
