package Actividad2.ModeloProductorConsumidor.Actividad2_10_PINGPONG;

public class Productor extends Thread {
    private ColaPing cola;

    public Productor(ColaPing c) {
        this.cola = c;
    }

    public void run() {
        String cadena = "";
        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {
                cadena = " PING ";
            } else {
                cadena = " PONG ";
            }
            cola.put(cadena);
        }
    }
}
