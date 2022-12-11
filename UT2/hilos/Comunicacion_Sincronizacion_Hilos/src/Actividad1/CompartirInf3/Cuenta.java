package Actividad1.CompartirInf3;

public class Cuenta {
    private int saldo;

    public Cuenta(int s) {
        this.saldo = s;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void restar(int cantidad) {
        this.saldo -= cantidad;
    }

    public void retirarDinero(int cantidad, String nombre) {
        if (getSaldo() >= cantidad) {
            System.out.println(nombre + ": SE VA A RETIRAR SALDO (ACTUAL ES: " + getSaldo() + ")");

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            restar(cantidad);

            System.out.println("\t" + nombre + " retira => " + cantidad + " ACTUAL (" + getSaldo() + ")");
        }
        else {
            System.out.println(nombre + " No puede retirar dinero, NO HAY SALDO (" + getSaldo() + ")");
        }

        if (getSaldo() < 0) {
            System.out.println("SALDO NEGATIVO => " + getSaldo());
        }
    }
}
