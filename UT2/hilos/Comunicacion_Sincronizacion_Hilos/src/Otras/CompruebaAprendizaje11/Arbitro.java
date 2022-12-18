package Otras.CompruebaAprendizaje11;

public class Arbitro {
    private int numJugadores;
    private int turno;
    private int numAdivinar;
    private boolean finJuego = false;

    public Arbitro(int numJugadores) {
        this.numJugadores = numJugadores;
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

        } else {
            this.finJuego = true;
            System.out.println("FIN DEL JUEGO - Ha ganado el jugador " + jugadorId);
        }
    }
}
