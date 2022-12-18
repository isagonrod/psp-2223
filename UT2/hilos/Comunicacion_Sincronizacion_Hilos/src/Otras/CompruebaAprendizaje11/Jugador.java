package Otras.CompruebaAprendizaje11;

import Util.Teclado;

public class Jugador extends Thread {
    private int id;
    private Arbitro arbitro;

    public Jugador(int idJugador, Arbitro arbitro) {
        this.id = idJugador;
        this.arbitro = arbitro;
    }

    public void run() {
        do {
            if (arbitro.getTurno() == id) {
                arbitro.jugada(id, Teclado.getInt("Jugador " + this.id + ", introduce un número para tu jugada: "));
            }
            else {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } while (!arbitro.isFinJuego());
    }
}
