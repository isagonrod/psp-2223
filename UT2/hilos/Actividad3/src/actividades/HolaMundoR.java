package actividades;

/* Comprueba tu aprendizaje - Ejercicio 2
 * Crea una clase que implemente la interfaz Runnable cuya única funcionalidad sea visualizar el mensaje "Hola mundo"
 * seguido de una cadena que se recibirá en el constructor (es decir, al crear un objeto de este tipo se enviará una cadena)
 * y seguido del identificador del hilo.
 * Crea un programa Java que visualice el mensaje anterior 5 veces creando para ello 5 hilos diferentes usando la clase
 * creada anteriormente.
 * Luego haz que antes de visualizar el mensaje el hilo espere un tiempo proporcional a su identificador; usa para ello
 * el método sleep().
 * ¿Qué diferencias observas al ejecutar el programa usando o no el método sleep()?
 */

public class HolaMundoR implements Runnable {
    private String cadena;

    public HolaMundoR(String cadena) {
        this.cadena = cadena;
    }

    @Override
    public void run() {
        System.out.println("Hola mundo | " + this.cadena + " | Id del hilo: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) throws InterruptedException {
        HolaMundoR hilo1 = new HolaMundoR("hello");
        HolaMundoR hilo2 = new HolaMundoR("bonjour");
        HolaMundoR hilo3 = new HolaMundoR("ciao");
        HolaMundoR hilo4 = new HolaMundoR("hola");
        HolaMundoR hilo5 = new HolaMundoR("hallo");

        Thread.sleep(Thread.currentThread().getId() * 1500);
        new Thread(hilo1).start();

        Thread.sleep(Thread.currentThread().getId() * 1500);
        new Thread(hilo2).start();

        Thread.sleep(Thread.currentThread().getId() * 1500);
        new Thread(hilo3).start();

        Thread.sleep(Thread.currentThread().getId() * 1500);
        new Thread(hilo4).start();

        Thread.sleep(Thread.currentThread().getId() * 1500);
        new Thread(hilo5).start();
    }
}
