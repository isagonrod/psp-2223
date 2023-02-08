package ejemplo5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPObjetoServidor1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int numeroPuerto = 6000; // Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);

        System.out.println("Esperando al cliente...");
        Socket cliente = servidor.accept();

        // Se prepara el flujo de salida para objetos
        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

        // Se prepara un objeto y se envía
        Persona persona = new Persona("Juan", 20);
        outObjeto.writeObject(persona); // Enviando objeto
        System.out.println("Envío: " + persona.getNombre() + " * " + persona.getEdad());

        // Se obtiene un stream para leer objetos
        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
        Persona dato = (Persona) inObjeto.readObject();

        System.out.println("Recibo: " + dato.getNombre() + " * " + dato.getEdad());

        // CERRAR STREAMS Y SOCKETS
        outObjeto.close();
        inObjeto.close();
        cliente.close();
        servidor.close();
    }
}
