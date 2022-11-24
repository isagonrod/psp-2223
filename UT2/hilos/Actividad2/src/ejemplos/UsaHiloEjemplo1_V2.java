package ejemplos;

public class UsaHiloEjemplo1_V2 {
    public static void main(String[] args) {
        HiloEjemplo1_V2 hilo1 = new HiloEjemplo1_V2("Hilo 1");
        HiloEjemplo1_V2 hilo2 = new HiloEjemplo1_V2("Hilo 2");
        HiloEjemplo1_V2 hilo3 = new HiloEjemplo1_V2("Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        System.out.println("3 hilos inicializados...");
    }
}
