package ejercicio9;

public class HiloInterrumpir extends Thread {
    int contador = 0;
    int tiempo;

    HiloInterrumpir(String nombre, int tiempo) {
        this.setName(nombre);
        this.tiempo = tiempo;
    }

    public void interrumpirHilo() {
        interrupt();
    }

    public int getContador() {
        return contador;
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                contador++;
                sleep(tiempo);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Fin del hilo: " + getName());
    }
}
