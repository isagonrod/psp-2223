package prueba2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
    BufferedReader mensajeEntrada;
    PrintWriter mensajeSalida;
    Socket cliente;

    // Constructor donde se inicializan los atributos de la clase HiloServidor
    public HiloServidor(Socket socket) throws IOException {
        // Cliente que está utilizando el hilo
        this.cliente = socket;
        // Mensaje de salida del cliente
        this.mensajeSalida = new PrintWriter(socket.getOutputStream(), true);
        // Mensaje de entrada del servidor hacia el cliente
        this.mensajeEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        // Al arrancar el hilo, lee el comando escrito por el cliente
        // Si es distinto de 'exit', lanza el método 'process' y envía la respuesta del servidor al cliente
        for(String comando = ""; !comando.equalsIgnoreCase("exit"); this.mensajeSalida.println(LectorDeComandos.process(this.cliente, comando))) {
            try {
                comando = this.mensajeEntrada.readLine();
            } catch (IOException var4) {
                throw new RuntimeException();
            }
        }

        // Se cierran los procesos
        try {
            this.mensajeSalida.close();
            this.mensajeEntrada.close();
            this.cliente.close();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
