package Actividad2.ModeloProductorConsumidor.Actividad2_10_PINGPONG;

public class Consumidor extends Thread {
    private ColaPing cola;

    public Consumidor(ColaPing c) {
        this.cola = c;
    }

    public void run() {
        String valor = "";
        for (int i = 0; i < 40; i++) {
            valor = cola.get();
            System.out.println(valor);
        }
    }
}
