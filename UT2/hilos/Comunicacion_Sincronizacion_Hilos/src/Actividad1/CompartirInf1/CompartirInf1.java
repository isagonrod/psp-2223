package Actividad1.CompartirInf1;

import Actividad1.Contador;

/* CompartirInf1
 * Hilos que comparten información, en concreto acceden a un objeto Contador.
 * No hay ningún mecanismo de sincronización por lo que el valor del objeto Contador puede variar en cada ejecución.
 */

public class CompartirInf1 {
    public static void main(String[] args) {
        Contador cont = new Contador(100);
        HiloA hiloA = new HiloA("Hilo A", cont);
        HiloB hiloB = new HiloB("Hilo B", cont);

        hiloA.start();
        hiloB.start();
    }
}
