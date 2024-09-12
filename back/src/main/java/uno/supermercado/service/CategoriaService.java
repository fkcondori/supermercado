package uno.supermercado.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;

import uno.supermercado.exception.EliminarException;
import uno.supermercado.model.Categoria;
import uno.supermercado.model.Producto;
import uno.supermercado.repository.CategoriaRepository;
import uno.supermercado.repository.ProductoRepository;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Producto> getProductosByCategoriaId(Long id) {
        Categoria categoria = findById(id);
        return categoria != null ? categoria.getProductos() : null;
    }

    @Transactional
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        // Verificar si la categoría tiene productos asociados
        if (productoRepository.findByCategoriaId(id).size() > 0) {
            throw new EliminarException(
                    "La categoría tiene productos asociados y no puede ser eliminada.");
        }
        categoriaRepository.deleteById(id);
    }

}