package org.iesvi.ws;

public class CalculadoraConsumer {

    public static void main(String[] args) {
        CalculadoraImplService calculadoraService = new CalculadoraImplService();
        Calculadora consumer = calculadoraService.getCalculadoraImplPort();
        System.out.println("Suma");
        System.out.println(consumer.operation(1,6,4));
    }
}
