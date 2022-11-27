package ejemplos;

public class HiloEjemplo2Grupos extends Thread {
    public void run() {
        System.out.println("Información del hilo: " + Thread.currentThread());
        for (int i = 0; i < 1000; i++) {
            i++;
        }
        System.out.println(Thread.currentThread().getName() + " Finalizando la ejecución.");
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Principal");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread());

        ThreadGroup group = new ThreadGroup("Grupo de hilos");
        HiloEjemplo2Grupos hilos = new HiloEjemplo2Grupos();

        Thread hilo1 = new Thread(group, hilos, "Hilo 1");
        Thread hilo2 = new Thread(group, hilos, "Hilo 2");
        Thread hilo3 = new Thread(group, hilos, "Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        System.out.println("3 hilos creados...");
        System.out.println("Hilos activos: " + Thread.activeCount());
    }
}
