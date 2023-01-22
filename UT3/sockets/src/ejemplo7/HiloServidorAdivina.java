package ejemplo7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidorAdivina extends Thread {
    ObjectInputStream fentrada;
    ObjectOutputStream fsalida;
    Socket socket;
    ObjetoCompartido objeto;
    int identificador;
    int intentos = 0;

    public HiloServidorAdivina(Socket s, ObjetoCompartido objeto, int id) {
        this.socket = s;
        this.objeto = objeto;
        this.identificador = id;

        try {
            fsalida = new ObjectOutputStream(socket.getOutputStream());
            fentrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("ERROR DE E/S EN HiloServidor");
            ex.printStackTrace();
        }
    }

    public void run() {
        System.out.println("=> CLIENTE CONECTADO: " + identificador);

        // Preparar primer envío al cliente
        Datos datos = new Datos("Adivina un número entre 1 y 25", intentos, identificador);

        if (objeto.seAcabo()) {
            datos.setCadena("LO SENTIMOS, EL JUEGO HA TERMINADO, HAN ADIVINADO EL NÚMERO");
            datos.setJuega(false); // ya no tiene que jugar
        }

        try {
            fsalida.reset();
            fsalida.writeObject(datos);
        } catch (IOException ex) {
            System.out.println("Error en el Hilo al realizar el primer envío al jugador: " + identificador);
            return;
        }

        while (!objeto.seAcabo() || !(datos.getIntentos() == 5)) {
            int numClie = 0;

            try {
                // Recibir datos del cliente
                Datos d = (Datos) fentrada.readObject();
                numClie = Integer.parseInt(d.getCadena());
            } catch (IOException ex) {
                System.out.println("Error en el Hilo al leer del jugador: " + identificador);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("El jugador: " + identificador + " se ha desconectado");
                break;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                break;
            }

            // Jugar el número
            String cad = objeto.nuevaJugada(identificador, numClie);
            intentos++;

            datos = new Datos(cad, intentos, identificador);

            if (objeto.seAcabo()) {
                datos.setJuega(false); // no tiene que seguir jugando
                if (identificador == objeto.getGanador()) {
                    datos.setGana(true);
                }
            }

            try {
                // Enviar datos al cliente
                fsalida.reset();
                fsalida.writeObject(datos);
            } catch (IOException ex) {
                System.out.println("Error escribiendo en flujo de salida del jugador: " + identificador + " * " + ex.getMessage());
                break;
            } catch (NullPointerException ex) {
                System.out.println("El jugador " + identificador + " se ha desconectado");
                break;
            }
        }

        if (objeto.seAcabo()) {
            System.out.println("EL JUEGO SE HA ACABADO...");
            System.out.println("Desconecta: " + identificador);
        }

        try {
            fsalida.close();
            fentrada.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error en Hilo al cerrar cliente: " + identificador);
            ex.printStackTrace();
        }
    }
}
