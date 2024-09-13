package uno.supermercado.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import uno.supermercado.exception.ActualizarException;
import uno.supermercado.exception.EliminarException;
import uno.supermercado.model.Categoria;
import uno.supermercado.model.Producto;
import uno.supermercado.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public List<Producto> getCategoriaById(@PathVariable Long id) {
        List<Producto> lista = categoriaService.getProductosByCategoriaId(id);
        if (lista == null) {
            throw new RuntimeException("Categoria no encontrada - " + id);
        }
        return lista;
    }

    @GetMapping("/{id}/productos")
    public List<Producto> getProductosByCategoriaId(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            throw new RuntimeException("Categoría no encontrada - " + id);
        }
        return categoria.getProductos();
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoriaId(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria categoriaActual = categoriaService.findById(id);
        if (categoriaActual == null) {
            throw new ActualizarException("Categoria no encontrada - " + id);
        }
        categoriaActual.setNombre(categoria.getNombre());
        categoriaActual.setDescripcion(categoria.getDescripcion());
        categoriaActual.setFechaActualizacion(categoria.getFechaActualizacion());
        return categoriaService.save(categoriaActual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoriaId(@PathVariable Long id) {
        try {
            categoriaService.deleteById(id);
            return ResponseEntity.noContent().build(); // Código 204 No Content
        } catch (EliminarException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); // Código 400 Bad Request
        }
    }

}
