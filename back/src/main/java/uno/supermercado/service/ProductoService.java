package uno.supermercado.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uno.supermercado.model.Producto;
import uno.supermercado.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Producto save(Producto producto) {
        productoRepository.flush();
        return productoRepository.save(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> findByCategoriaId(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    // EN EL OTRO PROYECTO UTILIZAN EL PAQUETE NIO Y POR ESO NO DAN EXCEPCIONES
    public Resource getProductoImage(Long productoId) throws IOException {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Álbum no encontrado"));

        Path filePath = Paths.get("/home/keyla/Desktop/proyectos/supermercado/back/src/images/" + producto.getImagen());
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("No se pudo leer el archivo: " + filePath);
        }
    }

}