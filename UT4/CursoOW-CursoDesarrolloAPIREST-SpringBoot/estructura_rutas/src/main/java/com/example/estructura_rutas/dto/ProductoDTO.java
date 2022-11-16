package com.example.estructura_rutas.dto;

import lombok.Getter;
import lombok.Setter;

public class ProductoDTO {

    @Getter
    @Setter
    private Long id;
    private String nombre;
    private String categoriaNombre;
}
