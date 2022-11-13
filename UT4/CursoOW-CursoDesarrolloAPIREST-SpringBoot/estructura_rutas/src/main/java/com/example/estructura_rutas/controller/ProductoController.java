package com.example.estructura_rutas.controller;

import com.example.estructura_rutas.modelo.Producto;
import com.example.estructura_rutas.modelo.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoRepositorio productoRepositorio;

    /**
     * Obtenemos un producto en base a su ID
     *
     * @param id del producto
     * @return el producto encontrado
     */
    @GetMapping("/producto/{id}")
    public Producto obtenerUno(@PathVariable Long id) {
        return null;
    }

    /**
     * Insertamos un nuevo producto
     *
     * @param nuevo producto
     * @return producto introducido
     */
    @PostMapping("/producto")
    public Producto nuevoProducto(@RequestBody Producto nuevo) {
        return null;
    }

    /**
     * Editamos un producto
     *
     * @param editar producto
     * @param id del producto
     * @return producto editado
     */
    @PutMapping("/producto/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        return null;
    }

    /**
     * Borra un producto del catálogo en base a su id
     *
     * @param id del producto
     * @return producto borrado
     */
    @DeleteMapping("/producto/{id}")
    public Producto borrarProducto(@PathVariable Long id) {
        return null;
    }
}
