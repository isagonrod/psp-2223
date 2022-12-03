package Actividad1.CompartirInf2_version2;

import Actividad1.Contador;

public class HiloA_2_2 extends Thread {
    private Contador contador;

    public HiloA_2_2(String nombre, Contador contador) {
        setName(nombre);
        this.contador = contador;
    }

    public void run() {
        synchronized (contador) {
            for (int i = 0; i < 300; i++) {
                contador.incrementa();
                System.out.println(getName() + " contador vale " + contador.valor());
            }
        }
    }
}
