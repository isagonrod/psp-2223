package Actividad2.BloqueoHilos;

public class BloqueoHilos {
    public static void main(String[] args) {
        ObjetoCompartido com = new ObjetoCompartido();
        HiloCadena a = new HiloCadena (com, " A ");
        HiloCadena b = new HiloCadena (com, " B ");
        a.start();
        b.start();
    }
}
