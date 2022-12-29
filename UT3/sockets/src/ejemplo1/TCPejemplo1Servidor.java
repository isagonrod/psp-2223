package ejemplo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPejemplo1Servidor {
    public static void main(String[] args) throws IOException {
        int numeroPuerto = 6000; //Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        Socket clienteConectado = null;
        System.out.println("Esperando al cliente...");
        clienteConectado = servidor.accept();

        //CREACIÓN DE FLUJO DE ENTRADA DEL CLIENTE
        InputStream entrada = null;
        entrada = clienteConectado.getInputStream();
        DataInputStream flujoEntrada = new DataInputStream(entrada);

        //EL CLIENTE ENVÍA UN MENSAJE
        System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());

        //CREACIÓN DE FLUJO DE SALIDA PARA EL CLIENTE
        OutputStream salida = null;
        salida = clienteConectado.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);

        //ENVIAR UN SALUDO AL CLIENTE
        flujoSalida.writeUTF("Saludos al cliente del servidor");

        //CERRAR STREAMS Y SOCKETS
        entrada.close();
        flujoEntrada.close();
        salida.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();
    }
}
