package transformacion_ejemplos_actividad2;

public class HiloEjemplo1_R implements Runnable {
    private String name;

    public HiloEjemplo1_R(String name) {
        this.name = name;
        System.out.println("CREANDO HILO: " + getName());
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo: " + getName() + " C = " + i);
        }
    }

    public static void main(String[] args) {
        HiloEjemplo1_R hilo1 = new HiloEjemplo1_R("Hilo 1");
        HiloEjemplo1_R hilo2 = new HiloEjemplo1_R("Hilo 2");
        HiloEjemplo1_R hilo3 = new HiloEjemplo1_R("Hilo 3");

        new Thread(hilo1).start();
        new Thread(hilo2).start();
        new Thread(hilo3).start();
    }
}
