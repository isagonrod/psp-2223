package ejemplos;

public class PrimerHilo extends Thread {
    private int num;

    public PrimerHilo(int num) {
        this.num = num;
    }

    public void run() {
        for (int i = 0; i < num; i++) {
            System.out.println("En el hilo... " + i);
        }
    }

    public static void main(String[] args) {
        PrimerHilo primerHilo = new PrimerHilo(10);
        primerHilo.start();
    }
}
