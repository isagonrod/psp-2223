package Actividad1.CompartirInf2;

import Actividad1.Contador;

public class HiloB_2 extends Thread {
    private Contador contador;

    public HiloB_2(String nombre, Contador contador) {
        setName(nombre);
        this.contador = contador;
    }

    public void run() {
        synchronized (contador) {
            for (int i = 0; i < 300; i++) {
                contador.decrementa();
            }
            System.out.println(getName() + " contador vale " + contador.valor());
        }
    }
}
