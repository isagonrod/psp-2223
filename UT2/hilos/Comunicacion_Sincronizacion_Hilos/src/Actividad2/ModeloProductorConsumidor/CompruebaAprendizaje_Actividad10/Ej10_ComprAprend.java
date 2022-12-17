package Actividad2.ModeloProductorConsumidor.CompruebaAprendizaje_Actividad10;

/* COMPRUEBA TU APRENDIZAJE - ACTIVIDAD 10
 * Usando el modelo productor-consumidor, crea un productor que lea caracteres de un fichero de texto cuyo nombre se pasará
 * en el constructor.
 * Y un consumidor que obtenga los datos que produce el productor y los visualice en pantalla.
 * Muestra al final del proceso del productor y del consumir un mensaje indicando que el proceso ha finalizado.
 * Prueba el programa con varios consumidores, ¿finalizan el proceso todos los consumidores?; utiliza el método getState()
 * para comprobar el estado de los consumidores cuando el productor finaliza.
 * Intenta qu todos los consumidores finalicen correctamente.
 */

public class Ej10_ComprAprend {
    public static void main(String[] args) {
        Cola cola = new Cola();

        Productor_leeFichero p = new Productor_leeFichero(cola, "Fichero.txt");
        Consumidor_visualizaDatos c1 = new Consumidor_visualizaDatos(cola, 1);
        Consumidor_visualizaDatos c2 = new Consumidor_visualizaDatos(cola, 2);
        Consumidor_visualizaDatos c3 = new Consumidor_visualizaDatos(cola, 3);

        p.start();
        c1.start();
        c2.start();
        c3.start();

        try {
            p.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Estado Consumidor 1: " + c1.getState());
        System.out.println("Estado Consumidor 2: " + c2.getState());
        System.out.println("Estado Consumidor 3: " + c3.getState());

        if (c1.isAlive()) {
            cola.put(-1);
            c1.interrupt();
        }

        if (c2.isAlive()) {
            cola.put(-1);
            c2.interrupt();
        }

        if (c3.isAlive()) {
            cola.put(-1);
            c3.interrupt();
        }

        System.out.println("Estado Consumidor 1: " + c1.getState());
        System.out.println("Estado Consumidor 2: " + c2.getState());
        System.out.println("Estado Consumidor 3: " + c3.getState());
    }
}
