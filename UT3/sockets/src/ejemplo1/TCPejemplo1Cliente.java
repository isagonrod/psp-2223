package ejemplo1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPejemplo1Cliente {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int puerto = 6000; //Puerto remoto

        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, puerto);

        //CREACIÓN DE FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

        //ENVIAR UN SALUDO AL SERVIDOR
        flujoSalida.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

        //CREACIÓN DE FLUJO DE ENTRADA PARA EL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

        //EL SERVIDOR ENVÍA UN MENSAJE
        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

        //CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        cliente.close();
    }
}
