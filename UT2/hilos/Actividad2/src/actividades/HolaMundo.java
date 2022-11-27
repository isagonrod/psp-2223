package actividades;

/* Comprueba tu aprendizaje - Ejercicio 1
 * Crea una clase que extienda de Thread cuya única funcionalidad sea visualizar el mensaje "Hola mundo".
 * Crea un programa Java que visualice el mensaje anterior 5 veces creando para ello 5 hilos diferentes usando la clase
 * creada anteriormente.
 * Modifica el mensaje "Hola mundo" en el hilo para incluir el identificador del hilo.
 * Prueba de nuevo el programa Java creado anteriormente.
 */

public class HolaMundo extends Thread {
    public HolaMundo() {
        super("Hola mundo");
    }

    public void run() {
        // Primera parte del ejercicio
        //System.out.println(getName());

        //Segunda parte del ejercicio
        for (int i = 0; i < 5; i++) {
            System.out.println("Hilo " + (i + 1) + ": " + getName());
        }
    }

    public static void main(String[] args) {
        HolaMundo hilo1 = new HolaMundo();
        HolaMundo hilo2 = new HolaMundo();
        HolaMundo hilo3 = new HolaMundo();
        HolaMundo hilo4 = new HolaMundo();
        HolaMundo hilo5 = new HolaMundo();

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}
