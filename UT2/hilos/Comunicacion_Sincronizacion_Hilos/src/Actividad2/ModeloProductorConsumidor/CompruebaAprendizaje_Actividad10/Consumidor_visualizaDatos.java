package Actividad2.ModeloProductorConsumidor.CompruebaAprendizaje_Actividad10;

public class Consumidor_visualizaDatos extends Thread {
    private Cola cola;
    private int num;

    public Consumidor_visualizaDatos(Cola c, int n) {
        this.cola = c;
        this.num = n;
    }

    public void run() {
        char valor;
        int i = cola.get();
        int contadorCaracteres = 0; // salto de línea cada 5 caracteres
        while (i != -1) {
            System.out.println("%c [%d] - " + (char) i + " " + num);
            i = cola.get();
            contadorCaracteres++;
            if (contadorCaracteres % 5 == 0) {
                System.out.println("");
            }
            System.out.println("\nFin Consumidor " + num);
        }
    }
}
