package ejercicio8;

public class Hilo extends Thread {
    private SolicitarSuspender suspender = new SolicitarSuspender();
    private boolean stopHilo = false;
    int contador = 0;
    int tiempo;

    public Hilo(String nombre, int tiempo) {
        this.setName(nombre);
        this.tiempo = tiempo;
    }

    public void pararHilo() {
        this.stopHilo = true;
    }

    public int getContador() {
        return contador;
    }

    public void peticionSuspender() {
        this.suspender.set(true);
    }

    public void peticionContinuar() {
        this.suspender.set(false);
    }

    public void run() {
        try {
            while (!stopHilo) {
                contador++;
                sleep(tiempo);
                suspender.waitForResume();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Fin del hilo: " + getName());
    }
}
