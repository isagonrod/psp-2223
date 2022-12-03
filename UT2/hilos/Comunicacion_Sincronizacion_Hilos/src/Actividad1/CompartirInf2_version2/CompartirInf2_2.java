package Actividad1.CompartirInf2_version2;

import Actividad1.Contador;

/* CompartirInf2_version2
 * Variante del anterior donde el bloque sincronizado sólo afecta una pequeña parte del código.
 * Mostrar el valor del contador dentro del syncronized para comprobar como los 2 hilos van alternando el incremento/decremento.
 */

public class CompartirInf2_2 {
    public static void main(String[] args) {
        Contador cont = new Contador(100);
        HiloA_2_2 hiloA = new HiloA_2_2("Hilo A", cont);
        HiloB_2_2 hiloB = new HiloB_2_2("Hilo B", cont);

        hiloA.start();
        hiloB.start();
    }
}
