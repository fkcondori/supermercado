package uno.supermercado.controller;

import uno.supermercado.exception.ActualizarException;
import uno.supermercado.model.Categoria;
import uno.supermercado.model.Producto;
import uno.supermercado.service.CategoriaService;
import uno.supermercado.service.ProductoService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto == null) {
            throw new ActualizarException("Producto no encontrado - " + id);
        }
        return producto;
    }

    // @PostMapping
    // public Producto createProducto(@RequestBody Producto producto) {
    // return productoService.save(producto);

    // }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("origenProducto") String origenProducto,
            @RequestParam("marca") String marca,
            @RequestParam("precio") Double precio,
            @RequestParam("categoriaId") Long categoriaId,
            @RequestParam("imagen") MultipartFile imagen) {

        // Crear un nuevo producto
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setDescripcion(descripcion);
        nuevoProducto.setOrigenProducto(origenProducto);
        nuevoProducto.setMarca(marca);
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setCategoria(categoriaService.findById(categoriaId));

        // Manejo del archivo de imagen
        if (!imagen.isEmpty()) {
            String fileName = imagen.getOriginalFilename();
            nuevoProducto.setImagen(fileName); // Asume que tu modelo tiene un campo para almacenar el nombre de la
            try {
                // Guardar el archivo en el servidor
                FileOutputStream fileOutputStream = new FileOutputStream("src/images/" + fileName);
                fileOutputStream.write(imagen.getBytes());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Producto productoCreado = productoService.save(nuevoProducto);

        return ResponseEntity.ok(productoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("origenProducto") String origenProducto,
            @RequestParam("marca") String marca,
            @RequestParam("precio") Double precio,
            @RequestParam("categoriaId") Long categoriaId,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        // Buscar el producto existente
        Producto producto = productoService.findById(id);
        if (producto == null) {
            throw new ActualizarException("Producto no encontrado - " + id);
        }

        // Actualizar los campos del producto
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setOrigenProducto(origenProducto);
        producto.setMarca(marca);
        producto.setPrecio(precio);

        // Verificar si la categoría existe antes de asignarla
        Categoria categoria = categoriaService.findById(categoriaId);
        if (categoria == null) {
            throw new ActualizarException("Categoría no encontrada - " + categoriaId);
        }
        producto.setCategoria(categoria);

        // Si se ha enviado una nueva imagen, actualiza el campo de imagen
        if (imagen != null && !imagen.isEmpty()) {
            try {
                // Guardar el archivo en el servidor
                String fileName = imagen.getOriginalFilename();
                Path path = Paths.get("src/images/" + fileName);

                // Guardar la imagen en el servidor
                Files.write(path, imagen.getBytes());
                producto.setImagen(fileName); // Guardar el nombre del archivo en la base de datos
            } catch (IOException e) {
                throw new ActualizarException("Error al guardar la imagen - " + e.getMessage());
            }
        }

        // Guardar el producto actualizado
        Producto productoActualizado = productoService.save(producto);

        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getProductoImage(@PathVariable Long id) {
        try {
            Resource resource = productoService.getProductoImage(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
