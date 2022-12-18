package Otras.CompruebaAprendizaje11;

/* COMPRUEBA TU APRENDIZAJE - ACTIVIDAD 11
 * Se trata de simular el juego para adivinar un número.
 * Se crearán varios hilos, los hilos son los jugadores que tienen que adivinar el número.
 * Habrá un árbitro que generará el número a adivinar, comprobará la jugada del jugador y averiguará a qué jugador
 * le toca jugar.
 * El número tiene que estar comprendido entre 1 y 10, usa la siguiente fórmula para generar el número: 1 + (int)(10 * Math.random());
 *
 * Se definen 3 clases:
 * - Árbitro: contiene el número a adivinar, el turno y muestra el resultado.
 *      Se definen los siguientes atributos: el número total de jugadores, el turno, el número a adivinar y si el juego
 *      acabó o no.
 *      En el constructor se recibe el número de jugadores que participan y se inicializan el número a adivinar y el turno.
 *      Tiene varios métodos: uno que comprueba la jugada del jugador y averigua a quien le toca a continuación,
 *      este método recibirá el identificador de jugador y el número que ha jugado; deberá definirse como synchronized,
 *      así cuando un jugador está haciendo la jugada, ningún otro podrá interferir.
 *      En este método se indicará cuál es el siguiente turno y si el juego ha finalizado porque algún jugador ha acertado
 *      el número.
 * - Jugador: extiende de Thread.
 *      Su constructor recibe un identificador de jugador y el árbitro, todos los hilos comparten el árbitro.
 *      El jugador dentro del método run creará la jugada usando el método correspondiente del árbitro.
 *      Este proceso se repetirá hasta que el juego se acabe.
 * - Main: esta clase inicializa el árbitro indicándole el número de jugadores y lanza los hilos de los jugadores,
 *      asignando un identificador a cada hilo y enviándoles el objeto árbitro que tienen que compartir.
 */

import Util.Teclado;

public class ComprAprend11 {
    public static void main(String[] args) {
        int numJugadores = Teclado.getInt("Número de jugadores: ");

        Arbitro arbitro = new Arbitro(numJugadores);
        Jugador jugador;

        for (int i = 0; i < numJugadores; i++) {
            jugador = new Jugador(i + 1, arbitro);
            jugador.start();
        }
    }
}
