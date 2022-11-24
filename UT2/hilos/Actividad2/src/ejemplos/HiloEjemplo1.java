package ejemplos;

public class HiloEjemplo1 extends Thread {
    public HiloEjemplo1(String nombre) {
        super(nombre);
        System.out.println("Creando hilo: " + getName());
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo: " + getName() + " C = " + i);
        }
    }

    public static void main(String[] args) {
        HiloEjemplo1 hilo1 = new HiloEjemplo1("Hilo 1");
        HiloEjemplo1 hilo2 = new HiloEjemplo1("Hilo 2");
        HiloEjemplo1 hilo3 = new HiloEjemplo1("Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        System.out.println("3 hilos iniciados...");
    }
}
