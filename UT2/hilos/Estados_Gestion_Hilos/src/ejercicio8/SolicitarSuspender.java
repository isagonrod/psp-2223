package ejercicio8;

public class SolicitarSuspender {
    private boolean solicitarSuspender = false;

    public synchronized void set (boolean b) {
        this.solicitarSuspender = b;
        notifyAll();
    }

    public synchronized void waitForResume() throws InterruptedException {
        while (solicitarSuspender) {
            wait();
        }
    }
}
