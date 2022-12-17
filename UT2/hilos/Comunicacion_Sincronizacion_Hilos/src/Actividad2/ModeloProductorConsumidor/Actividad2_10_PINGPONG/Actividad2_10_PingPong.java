package Actividad2.ModeloProductorConsumidor.Actividad2_10_PINGPONG;

public class Actividad2_10_PingPong {
    public static void main(String[] args) {
        ColaPing cola = new ColaPing();
        Productor p = new Productor(cola);
        Consumidor c = new Consumidor(cola);

        p.start();
        c.start();
    }
}
