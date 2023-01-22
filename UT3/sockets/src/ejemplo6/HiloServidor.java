package ejemplo6;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {
    BufferedReader fentrada;
    PrintWriter fsalida;
    Socket socket;

    public HiloServidor(Socket s) throws IOException { // Constructor
        this.socket = s;

        // Se crean flujos de entrada y salida con el cliente
        fsalida = new PrintWriter(socket.getOutputStream(), true);
        fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        String cadena = "";
        System.out.println("COMUNICO CON: " + socket.toString());

        while (!cadena.trim().equals("*")) {
            try {
                cadena = fentrada.readLine(); // obtener cadena
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            fsalida.println(cadena.trim().toUpperCase()); // enviar mayúscula
        }

        System.out.println("FIN CON: " + socket.toString());

         try {
            fsalida.close();
            fentrada.close();
            socket.close();
        } catch (IOException ex) {
             ex.printStackTrace();
         }
    }
}
