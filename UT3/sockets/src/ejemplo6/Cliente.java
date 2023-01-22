package ejemplo6;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 6000; // puerto remoto
        Socket cliente = new Socket(host, port);

        // Se crean los flujos de entrada y salida
        PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        // Flujo para entrada estándar
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String cadena, eco = "";

        do {
            System.out.println("Introduce cadena: ");
            cadena = in.readLine();
            fsalida.println(cadena); // envío cadena al servidor
            eco = fentrada.readLine(); // recibo cadena del servidor
            System.out.println("    => ECO: " + eco);
        } while (!cadena.trim().equals("*"));

        fsalida.close();
        fentrada.close();
        System.out.println("Fin del envío...");
        in.close();
        cliente.close();
    }
}
