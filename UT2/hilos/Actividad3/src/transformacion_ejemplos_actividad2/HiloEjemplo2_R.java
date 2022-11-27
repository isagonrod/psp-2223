package transformacion_ejemplos_actividad2;

public class HiloEjemplo2_R implements Runnable {
    @Override
    public void run() {
        System.out.println(
                "Dentro del Hilo    : " + Thread.currentThread().getName() +
                "\n\tPrioridad      : " + Thread.currentThread().getPriority() +
                "\n\tID             : " + Thread.currentThread().getId() +
                "\n\tHilos activos  : " + Thread.activeCount()
        );
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Principal");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread());

        HiloEjemplo2_R hilo = new HiloEjemplo2_R();

        for (int i = 0; i < 3; i++) {
            Thread h = new Thread(hilo);
            h.setName("HILO " + i);
            h.setPriority(i + 1);
            h.start();
            System.out.println("Información del " + h.getName() + ": " + h);
        }

        System.out.println("3 HILOS CREADOS...");
        System.out.println("Hilos activos: " + Thread.activeCount());
    }
}
