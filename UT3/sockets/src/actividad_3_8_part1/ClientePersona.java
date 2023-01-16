package actividad_3_8_part1;

import ejemplo5.Persona;
import util.Teclado;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientePersona {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int puerto = 6000;

        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, puerto);

        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

        Persona persona = new Persona(Teclado.getString("Nombre: "), Teclado.getNum("Edad: "));
        outObjeto.writeObject(persona);
        System.out.println("Envío: " + persona.getNombre() + " - " + persona.getEdad());

        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
        Persona dato = (Persona) inObjeto.readObject();

        System.out.println("Recibo: " + dato.getNombre() + " - " + dato.getEdad());

        outObjeto.close();
        inObjeto.close();
        cliente.close();
    }
}
