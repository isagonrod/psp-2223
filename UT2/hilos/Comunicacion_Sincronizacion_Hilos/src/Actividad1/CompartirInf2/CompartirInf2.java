package Actividad1.CompartirInf2;

import Actividad1.Contador;

public class CompartirInf2 {
    public static void main(String[] args) {
        Contador cont = new Contador(100);
        HiloA_2 hiloA = new HiloA_2("Hilo A", cont);
        HiloB_2 hiloB = new HiloB_2("Hilo B", cont);

        hiloB.start();
        hiloA.start();
    }
}
