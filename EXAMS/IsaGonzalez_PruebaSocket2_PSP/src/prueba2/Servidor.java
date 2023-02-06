package prueba2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    // Constructor vacío requerido
    public Servidor() {
    }

    public static void main(String[] args) throws IOException {

        // Se crea un objeto ServerSocket para el servidor en el puerto que después habrá que ponerle también al cliente
        ServerSocket servidor = new ServerSocket(5000);

        // Se crea un atributo para contabilizar el número de clientes que se conectan, inicializándolo a 0 inicialmente
        int cantidadClientes = 0;

        // Mientras el número de clientes no sea superior a 3 se hará el siguiente proceso
        while(cantidadClientes <= 3) {
            // El cliente quedará a la espera de que el cliente se conecte y aceptará su conexión
            Socket cliente = servidor.accept();
            // Se aumentará en 1 el número de clientes conectados
            ++cantidadClientes;
            // Se crea un hilo para el cliente que se ha conectado para poder atenderlo
            HiloServidor hilo = new HiloServidor(cliente);
            // Se inicia el hilo
            hilo.start();

            // Si el número de clientes es superior a 3, se mostrará un mensaje en la pantalla del servidor y se cerrará el socket
            if (cantidadClientes > 3) {
                System.out.println("Servidor> SERVIDOR FINALIZADO");
                servidor.close();
            }
        }

    }
}
