package com.example.estructura_rutas.dto.converter;

import com.example.estructura_rutas.dto.ProductoDTO;
import com.example.estructura_rutas.modelo.Producto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {
    private final ModelMapper modelMapper;

    public ProductoDTO converToDto(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }
}
