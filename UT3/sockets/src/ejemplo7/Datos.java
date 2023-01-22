package ejemplo7;

import java.io.Serializable;

public class Datos implements Serializable {
    private String cadena; // cadena que se intercambia con el servidor
    private int intentos; // intentos que lleva el jugador, hasta 5
    private int identificador; // id del jugador
    private boolean gana; // true si el jugador adivina el número
    private boolean juega; // true si el jugador juega, false juego finalizado

    public Datos(String cadena, int intentos, int id) {
        this.cadena = cadena;
        this.intentos = intentos;
        this.identificador = id;
        this.gana = false;
        this.juega = true;
    }

    public Datos() {
        super();
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public boolean isGana() {
        return gana;
    }

    public void setGana(boolean gana) {
        this.gana = gana;
    }

    public boolean isJuega() {
        return juega;
    }

    public void setJuega(boolean juega) {
        this.juega = juega;
    }
}
