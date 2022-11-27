package transformacion_ejemplos_actividad2;

public class UsaHiloEjemplo1_V2_R {
    public static void main(String[] args) {
        HiloEjemplo1_V2_R hilo1 = new HiloEjemplo1_V2_R("Hilo 1");
        HiloEjemplo1_V2_R hilo2 = new HiloEjemplo1_V2_R("Hilo 2");
        HiloEjemplo1_V2_R hilo3 = new HiloEjemplo1_V2_R("Hilo 3");

        new Thread(hilo1).start();
        new Thread(hilo2).start();
        new Thread(hilo3).start();

        System.out.println("3 HILOS INICIADOS...");
    }
}
