package Actividad2.ModeloProductorConsumidor.Version2;

public class Produc_Consum2 {
    public static void main(String[] args) {
        ColaSincronizada cola = new ColaSincronizada();
        ProductorSincronizado productor = new ProductorSincronizado(cola, 1);
        ConsumidorSincronizado consumidor = new ConsumidorSincronizado(cola, 1);
        productor.start();
        consumidor.start();
    }
}
