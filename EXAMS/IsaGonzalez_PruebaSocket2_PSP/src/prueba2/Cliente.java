package prueba2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    // Constructor vacío requerido
    public Cliente() {
    }

    public static void main(String[] args) throws Exception {

        // Se crea un socket para el cliente con el mismo puerto que el servidor para poder comunicar a ambos
        Socket cliente = new Socket("localhost", 5000);

        // Se inicializa un objeto de la clase PrintWriter para enviar al servidor el mensaje deseado
        PrintWriter mensajeSalida = new PrintWriter(cliente.getOutputStream(), true);

        // Se inicializa un objeto de la clase BufferedReader para el mensaje que recibe el cliente desde el servidor y otro para leer por teclado desde el cliente
        BufferedReader mensajeEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        String comando;
        String respuesta;

        do {
            // Se muestra un menú de opciones al cliente para que escriba una de las tres opciones que se le muestra
            LectorDeComandos.menu();
            System.out.println("Cliente> ");
            // El cliente escribe el comando deseado del menú
            comando = entrada.readLine();
            // El comando se envía al servidor
            mensajeSalida.println(comando);
            // El servidor devuelve la respuesta
            respuesta = mensajeEntrada.readLine();
            // Se muestra por pantalla la respuesta del servidor
            System.out.println("Servidor> " + respuesta);
        }
        // Mientras el cliente no escriba el comando 'exit', seguirá apareciendo el menú cada vez que el servidor le envíe su respuesta
        while(!comando.equalsIgnoreCase("exit"));

        // Cerramos los procesos
        mensajeSalida.close();
        mensajeEntrada.close();
        entrada.close();
        cliente.close();
    }
}
