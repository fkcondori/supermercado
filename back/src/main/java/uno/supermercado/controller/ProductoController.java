package uno.supermercado.controller;

import uno.supermercado.exception.ActualizarException;
import uno.supermercado.model.Producto;
import uno.supermercado.service.ProductoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

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

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.save(producto);

    }

    @PutMapping("/{id}")
    public Producto updateProductoById(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existProducto = productoService.findById(id);
        if (existProducto == null) {
            throw new ActualizarException("Producto no encontrado - " + id);
        }
        producto.setIdProducto(id);
        return productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable int id) {

    }
}
