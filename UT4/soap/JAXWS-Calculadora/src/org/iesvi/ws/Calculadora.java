package org.iesvi.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Calculadora {
    // Exponemos método para que sea accesible
    @WebMethod
    public double operation(int option, int value1, int value2);
}
