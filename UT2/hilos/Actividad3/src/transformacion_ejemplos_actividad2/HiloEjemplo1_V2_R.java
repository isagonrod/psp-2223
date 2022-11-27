package transformacion_ejemplos_actividad2;

public class HiloEjemplo1_V2_R implements Runnable {
    private String name;

    public HiloEjemplo1_V2_R(String name) {
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
}
