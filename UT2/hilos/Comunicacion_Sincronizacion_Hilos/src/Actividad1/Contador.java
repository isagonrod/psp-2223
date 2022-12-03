package Actividad1;

public class Contador {
    private int contador = 0;

    public Contador(int contador) {
        this.contador = contador;
    }

    public void incrementa() {
        this.contador++;
    }

    public void decrementa() {
        this.contador--;
    }

    public int valor() {
        return this.contador;
    }
}
