package Actividad1;

public class HiloA extends Thread {
    private Contador contador;

    public HiloA(String nombre, Contador contador) {
        setName(nombre);
        this.contador = contador;
    }

    public void run() {
        for (int i = 0; i < 300; i++) {
            contador.incrementa();
//            try {
//                sleep(100);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
            System.out.println(getName() + " contador valor " + contador.valor());
        }
    }
}
