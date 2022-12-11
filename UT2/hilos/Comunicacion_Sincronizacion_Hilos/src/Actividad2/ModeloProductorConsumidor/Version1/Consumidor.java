package Actividad2.ModeloProductorConsumidor.Version1;

import java.sql.Connection;

public class Consumidor extends Thread {
    private Cola cola;
    private int num;

    public Consumidor(Cola c, int n) {
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
