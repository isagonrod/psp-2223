package Actividad1.CompartirInf3;

public class SacarDinero extends Thread {
    private Cuenta c;
    private String nombre;

    public SacarDinero(String nombre, Cuenta cuenta) {
        super(nombre);
        this.c = cuenta;
    }

    public void run() {
        for (int i = 0; i <= 4; i++) {
            c.retirarDinero(10, getName());
        }
    }
}
