package actividad_3_8;

/*
 * ACTIVIDAD 3.8 (página 150 del libro)
 *
 * Parte 1:
 * Usando sockets UDP, realiza un programa servidor que espere un datagrama de un cliente.
 * El cliente le envía un objeto Persona que previamente había inicializado.
 * El servidor modifica los datos del objeto Persona y se lo envía de vuelta al cliente.
 * Visualiza los datos del objeto Persona tanto en el programa cliente cuando los envía y los recibe como en el
 * programa servidor cuando los recibe y los envía modificados.
 *
 * Parte 2:
 * Realiza la Actividad 3.7 con sockets UDP.
 *
 * Parte 3:
 * Realiza el Ejercicio 3.
 */

import ejemplo5.Persona;
import util.Teclado;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorPersona {
    public static void main(String[] args) throws Exception {
        int puerto = 6000;
        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("Esperando al cliente...");
        Socket cliente = servidor.accept();

        ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());

        Persona dato = (Persona) perEnt.readObject();
        System.out.println("Recibo: " + dato.getNombre() + " - " + dato.getEdad());

        dato.setNombre(Teclado.getString("Nuevo nombre: "));
        dato.setEdad(Teclado.getNum("Nueva edad: "));

        ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
        perSal.writeObject(dato);
        System.out.println("Envío: " + dato.getNombre() + " - " + dato.getEdad());

        perEnt.close();
        perSal.close();
        cliente.close();
        servidor.close();
    }
}
