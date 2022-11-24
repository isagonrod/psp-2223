package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner lombokTest() {
        return args -> {

            Persona persona1 = new Persona();
            persona1.setDni("12345678A");
            persona1.setNombre("Pepe");
            persona1.setApellidos("Pérez");

            System.out.println(persona1);

            Persona persona2 = new Persona("12345678A", "Paco", "Pérez");

            System.out.println(persona2);

            if (persona1.equals(persona2)) {
                System.out.println("Ambas personas son iguales");
            } else {
                System.out.println("Las dos personas no son iguales");
            }
        };
    }
}
