package Actividad2.ModeloProductorConsumidor.Actividad2_10;

/* ACTIVIDAD 2.10
 * Prueba el ejemplo anterior usando 2 consumidores y un productor.
 * La salida debe ser parecida a esta, en el productor todas las iteracciones, en los consumidores no, ya que solo se
 * producen números en 5 iteracciones:
 *
 *  [ver secuencia en libro]
 *
 * Modifica la clase Productor para que envíe las cadenas PING y PONG (de forma alternativa, una vez PING y otra PONG)
 * a la cola y la clase Consumidor tome la cadena de la cola y la visualice.
 * La salida tiene que mostrar lo siguiente: PING PONG PING PONG PING PONG...
 */

public class Actividad2_10 {
    public static void main(String[] args) {
        ColaAct cola = new ColaAct();
        ProductorAct productor = new ProductorAct(cola, 1);
        ConsumidorAct consumidor1 = new ConsumidorAct(cola, 1);
        ConsumidorAct consumidor2 = new ConsumidorAct(cola, 2);

        productor.start();
        consumidor1.start();
        consumidor2.start();
    }
}
