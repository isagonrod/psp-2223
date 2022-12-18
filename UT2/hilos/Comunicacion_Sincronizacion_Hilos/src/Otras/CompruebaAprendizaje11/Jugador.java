package Otras.CompruebaAprendizaje11;

import Util.Teclado;

import java.util.Scanner;

public class Jugador extends Thread {
    private int id;
    private Arbitro arbitro;

    public Jugador(int idJugador, Arbitro arbitro) {
        this.id = idJugador;
        this.arbitro = arbitro;
    }

    public void run() {
        do {
            arbitro.jugada(id, Teclado.getInt("Introduce un número para tu jugada: "));
        } while (!arbitro.isFinJuego());
    }
}
