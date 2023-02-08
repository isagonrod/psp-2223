package ejemplo8;

import java.net.Socket;

public class ComunHilos {
    private int conexiones; // Número de conexiones totales, ocupadas en el array
    private int actuales; // Número de conexiones actuales
    private int maximo; // Máximo de conexiones permitidas
    Socket[] tabla = new Socket[maximo]; // Sockets conectados
    String mensajes; // Mensajes del chat

    public ComunHilos(int maximo, int actuales, int conexiones, Socket[] tabla) {
        this.maximo = maximo;
        this.actuales = actuales;
        this.conexiones = conexiones;
        this.tabla = tabla;
        this.mensajes = "";
    }

    public ComunHilos() {
        super();
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public int getConexiones() {
        return conexiones;
    }

    public synchronized void setConexiones(int conexiones) {
        this.conexiones = conexiones;
    }

    public String getMensajes() {
        return mensajes;
    }

    public synchronized void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public int getActuales() {
        return actuales;
    }

    public synchronized void setActuales(int actuales) {
        this.actuales = actuales;
    }

    // Añadir socket al array de sockets
    public synchronized void addTabla(Socket s, int i) {
        tabla[i] = s;
    }

    public Socket getElementoTabla(int i) {
        return tabla[i];
    }
}
