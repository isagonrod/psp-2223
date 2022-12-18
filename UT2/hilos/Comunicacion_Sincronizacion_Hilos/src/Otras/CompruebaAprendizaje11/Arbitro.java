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

    public synchronized void jugada(int jugadorId, int num) {
        if (num != numAdivinar) {
            this.turno++;
            this.finJuego = false;
        } else {
            this.finJuego = true;
            System.out.println("FIN DEL JUEGO - Ha ganado el jugador " + jugadorId);
        }
    }

}
