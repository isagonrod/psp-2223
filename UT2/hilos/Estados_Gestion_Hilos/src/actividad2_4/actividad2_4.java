package actividad2_4;

import java.util.Scanner;

public class actividad2_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MiHilo_actividad2_4 hilo = new MiHilo_actividad2_4();

        String cadena;
        while (true) {
            System.out.println("Introduce CADENA: ");
            cadena = sc.nextLine();

            if (!hilo.isAlive()) {
                System.out.println("Lanzando hilo...");
                hilo.start();
            }

            if (cadena.equalsIgnoreCase("*")) {
                break;
            }

            if (cadena.equalsIgnoreCase("S")) {
                hilo.Suspende();
            }

            if (cadena.equalsIgnoreCase("R")) {
                hilo.Reanuda();
            }
        }

        hilo.Reanuda();
        System.out.println("Contador: " + hilo.getContador());
        hilo.pararHilo();
    }
}
