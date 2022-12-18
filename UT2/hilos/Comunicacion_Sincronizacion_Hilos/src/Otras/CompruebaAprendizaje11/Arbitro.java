package Otras.CompruebaAprendizaje11;

import java.util.Map;

public class Arbitro {
    private final Map<Integer, Jugador> jugadores;
    private int numJugadores;
    private int turno;
    private int numAdivinar;
    private boolean finJuego = false;

    public Arbitro(int numJugadores, Map<Integer, Jugador> jugadores) {
        this.numJugadores = numJugadores;
        this.jugadores = jugadores;
        this.numAdivinar = (int)(Math.random()* 10 + 1);
        this.turno = 1;
    }

    public boolean isFinJuego() {
        return finJuego;
    }

    public synchronized void jugada(int jugadorId, int num) {
        this.turno = jugadorId;

        if (num != numAdivinar) {

            if (jugadorId < numJugadores) {
                this.turno = jugadorId + 1;
            } else {
                this.turno = 1;
            }

            this.finJuego = false;
            System.out.println("El jugador " + jugadorId + " ha fallado su turno");
            synchronized (jugadores.get(this.turno)) {
                jugadores.get(this.turno).notify();
            }
        } else {
            this.finJuego = true;
            System.out.println("FIN DEL JUEGO - Ha ganado el jugador " + jugadorId);
            this.finalizarPartida();
        }
    }

    public int getTurno() {
        return turno;
    }

    public void finalizarPartida() {
        for (Map.Entry<Integer, Jugador> jugador: jugadores.entrySet()) {
            synchronized (jugador.getValue()) {
                if (jugador.getValue().isAlive()) {
                    jugador.getValue().notify();
                }
            }
        }
    }
}