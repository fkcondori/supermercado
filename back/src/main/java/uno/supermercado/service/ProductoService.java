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

    @Transactional(readOnly = true)
    public Producto save(Producto producto) {
        producto.setImagen(concatenateRandomString(producto.getImagen()));
        return productoRepository.save(producto);
    }

    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    // EN EL OTRO PROYECTO UTILIZAN EL PAQUETE NIO Y POR ESO NO DAN EXCEPCIONES
    public Resource getProductoImage(Long albumId) throws IOException {
        Producto producto = productoRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Álbum no encontrado"));

        Path filePath = Paths.get("/home/keyla/Desktop/proyectos/supermercado/back/src/images/leche.jpg");
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("No se pudo leer el archivo: " + filePath);
        }
    }

    private String concatenateRandomString(String imagen) {
        String randomString = generateRandomString(3);
        int dotIndex = imagen.lastIndexOf('.');
        String baseUrl = System.getProperty("base.url") + "/images/"; // Use System.getProperty() instead of
                                                                      // getProperty()
        if (dotIndex != -1) {
            return baseUrl + imagen.substring(0, dotIndex) + randomString + imagen.substring(dotIndex);
        } else {
            return baseUrl + imagen + randomString;
        }
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}