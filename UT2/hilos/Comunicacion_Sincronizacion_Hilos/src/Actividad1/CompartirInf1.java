package Actividad1;

public class CompartirInf1 {
    public static void main(String[] args) {
        Contador cont = new Contador(100);
        HiloA hiloA = new HiloA("Hilo A", cont);
        HiloB hiloB = new HiloB("Hilo B", cont);
        hiloA.start();
        hiloB.start();
    }
}
