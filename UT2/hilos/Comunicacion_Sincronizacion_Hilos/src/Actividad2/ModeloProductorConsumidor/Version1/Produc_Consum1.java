package Actividad2.ModeloProductorConsumidor.Version1;

public class Produc_Consum1 {
    public static void main(String[] args) {
        Cola cola = new Cola();
        Productor productor = new Productor(cola, 1);
        Consumidor consumidor = new Consumidor(cola, 1);
        productor.start();
        consumidor.start();
    }
}
