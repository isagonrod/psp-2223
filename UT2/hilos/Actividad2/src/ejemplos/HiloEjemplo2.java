package ejemplos;

public class HiloEjemplo2 extends Thread {
    public void run() {
        System.out.println(
                "Dentro del Hilo: " + Thread.currentThread().getName() +
                "\n\tPrioridad:     " + Thread.currentThread().getPriority() +
                "\n\tID:            " + Thread.currentThread().getId() +
                "\n\tHilos activos: " + Thread.currentThread().activeCount()
        );
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Principal"); // nombre del main
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().toString());

        HiloEjemplo2 hilo = null;

        for (int i = 0; i < 3; i++) {
            hilo = new HiloEjemplo2();  // crear hilo
            hilo.setName("Hilo " + i);  // damos nombre al hilo
            hilo.setPriority(i + 1);    // damos prioridad
            hilo.start();               // iniciar hilo
            System.out.println("Información del " + hilo.getName() + ": " + hilo.toString());
        }

        System.out.println("3 hilos creados...");
        System.out.println("Hilos activos: " + Thread.activeCount());
    }
}
