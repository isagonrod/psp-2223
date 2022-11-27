package transformacion_ejemplos_actividad2;

public class HiloEjemplo2Grupos_R implements Runnable {
    @Override
    public void run() {
        System.out.println("Información del hilo: " + Thread.currentThread());
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " Finalizando la ejecución.");
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Principal");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread());

        ThreadGroup group = new ThreadGroup("Grupo de hilos");
        HiloEjemplo2Grupos_R hilos = new HiloEjemplo2Grupos_R();

        Thread h1 = new Thread(group, hilos, "Hilo 1");
        Thread h2 = new Thread(group, hilos, "Hilo 2");
        Thread h3 = new Thread(group, hilos, "Hilo 3");

        h1.start();
        h2.start();
        h3.start();

        System.out.println("3 HILOS CREADOS...");
        System.out.println("Hilos activos: " + Thread.activeCount());
    }
}
