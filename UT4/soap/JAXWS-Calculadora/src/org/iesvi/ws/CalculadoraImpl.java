package org.iesvi.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "org.iesvi.ws.Calculadora")
public class CalculadoraImpl implements Calculadora {

    @Override
    public double operation(int option, int value1, int value2) {
        double result = 0;
        switch (option) {
            case 1:
                result = value1 + value2;
                break;
            case 2:
                result = value1 - value2;
                break;
            case 3:
                result = value1 * value2;
                break;
            case 4:
                result = value1 / value2;
                break;
        }
        return result;
    }
}
