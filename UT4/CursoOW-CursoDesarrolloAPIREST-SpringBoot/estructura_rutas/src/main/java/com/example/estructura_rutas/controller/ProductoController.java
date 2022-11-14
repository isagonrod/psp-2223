package com.example.estructura_rutas.controller;

import com.example.estructura_rutas.modelo.Producto;
import com.example.estructura_rutas.modelo.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoRepositorio productoRepositorio;

    /**
     * Obtenemos una lista de todos los productos
     *
     * @return 200 OK si obtiene resultados de la búsqueda, o un 404 Not Found si no es así.
     */
    @GetMapping("/producto/")
    public ResponseEntity<?> obtenerTodo() {
        List<Producto> result = productoRepositorio.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Obtenemos un producto en base a su ID
     *
     * @param id del producto
     * @return 200 OK si obtiene resultados de la búsqueda, o un 404 Not Found si no es así.
     */
    @GetMapping("/producto/{id}")
    public ResponseEntity<?> obtenerUno(@PathVariable Long id) {
        Producto result = productoRepositorio.findById(id).orElse(null);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Insertamos un nuevo producto
     *
     * @param nuevo producto
     * @return 201 Created si se ha insertado con éxito el nuevo producto
     */
    @PostMapping("/producto")
    public ResponseEntity<Producto> nuevoProducto(@RequestBody Producto nuevo) {
        Producto saved = productoRepositorio.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Editamos un producto
     *
     * @param editar producto
     * @param id del producto
     * @return 200 OK si se edita correctamente, o un 404 Not Found si no es así.
     */
    @PutMapping("/producto/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        return productoRepositorio.findById(id).map(p -> {
            p.setNombre(editar.getNombre());
            p.setPrecio(editar.getPrecio());
            return ResponseEntity.ok(productoRepositorio.save(p));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Borra un producto del catálogo en base a su id
     *
     * @param id del producto
     * @return 202 No Content si se ha borrado con éxito el producto y 404 Not Found si no se ha encontrado
     */
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        if (productoRepositorio.existsById(id)) {
            productoRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
