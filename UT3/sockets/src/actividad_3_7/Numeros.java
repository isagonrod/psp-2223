package actividad_3_7;

/*
 * ACTIVIDAD 3.7 (página 149 del libro)
 *
 * Crea una clase Java llamada Numeros que defina 3 atributos, uno de ellos entero, y los otros dos de tipo long:
 * - int numero;
 * - long cuadrado;
 * - long cubo;
 *
 * Define un constructor con parámetros y otro sin parámetros.
 * Define los métodos get y set de los atributos.
 *
 * Crea un programa cliente que introduzca por teclado un número e inicialice un objeto Numeros, el atributo numero debe
 * contener el número introducido por teclado.
 * Debe enviar ese objeto al programa servidor.
 * El proceso se repetirá mientras el número introducido por teclado sea mayor que 0.
 *
 * Crea un programa servidor que reciba un objeto Numeros.
 * Debe calcular el cuadrado y el cubo del atributo numero y debe enviar el objeto al cliente con los cálculos realizados,
 * el cuadrado y el cubo en sus atributos respectivos.
 * El cliente recibirá el objeto y visualizará el cuadrado y el cubo del número introducido por teclado.
 * El programa servidor finalizará cuando el número recibido en el objeto Numeros sea menor o igual que 0.
 *
 * Controlar posibles errores, por ejemplo si ejecutamos el cliente y el servidor no está iniciado, o si estando el
 * servidor ejecutándose ocurre algún error en el cliente, o este finaliza inesperadamente, etc.
 */

import java.io.Serializable;

public class Numeros implements Serializable {
    private int numero;
    private long cuadrado;
    private long cubo;

    public Numeros(int numero, long cuadrado, long cubo) {
        super();
        this.numero = numero;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    public Numeros(int numero) {
        super();
        this.numero = numero;
    }

    public Numeros() {
        super();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
}
