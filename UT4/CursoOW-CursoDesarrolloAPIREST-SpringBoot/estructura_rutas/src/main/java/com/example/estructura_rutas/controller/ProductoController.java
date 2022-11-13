package com.example.estructura_rutas.controller;

import com.example.estructura_rutas.modelo.Producto;
import com.example.estructura_rutas.modelo.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoRepositorio productoRepositorio;

    /**
     * Obtenemos una lista de todos los productos
     *
     * @return la lista de productos encontrados en el repositorio
     */
    @GetMapping("/producto/")
    public List<Producto> obtenerTodo() {
        return productoRepositorio.findAll();
    }

    /**
     * Obtenemos un producto en base a su ID
     *
     * @param id del producto
     * @return el producto encontrado o null si no encuentra el producto
     */
    @GetMapping("/producto/{id}")
    public Producto obtenerUno(@PathVariable Long id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    /**
     * Insertamos un nuevo producto
     *
     * @param nuevo producto
     * @return producto introducido
     */
    @PostMapping("/producto")
    public Producto nuevoProducto(@RequestBody Producto nuevo) {
        return productoRepositorio.save(nuevo);
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
        if (productoRepositorio.existsById(id)) {
            editar.setId(id);
            return productoRepositorio.save(editar);
        } else {
            return null;
        }
    }

    /**
     * Borra un producto del catálogo en base a su id
     *
     * @param id del producto
     * @return producto borrado
     */
    @DeleteMapping("/producto/{id}")
    public Producto borrarProducto(@PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            Producto result = productoRepositorio.findById(id).get();
            productoRepositorio.deleteById(id);
            return result;
        } else {
            return null;
        }
    }
}
