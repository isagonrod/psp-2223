package Actividad1.CompartirInf1;

import Actividad1.Contador;

public class HiloB extends Thread {
    private Contador contador;

    public HiloB(String nombre, Contador contador) {
        setName(nombre);
        this.contador = contador;
    }

    public void run() {
        for (int i = 0; i < 300; i++) {
            contador.decrementa();
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(getName() + " contador vale " + contador.valor());
        }
    }
}
