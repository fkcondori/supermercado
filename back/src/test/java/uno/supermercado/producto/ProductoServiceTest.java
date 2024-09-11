package uno.supermercado.producto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uno.supermercado.model.Producto;
import uno.supermercado.repository.ProductoRepository;
import uno.supermercado.service.ProductoService;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    public ProductoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        productoService.findAll();
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        when(productoRepository.findById(id)).thenReturn(Optional.of(new Producto()));
        productoService.findById(id);
        verify(productoRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Producto producto = new Producto();
        when(productoRepository.save(producto)).thenReturn(producto);
        Producto savedProducto = productoService.save(producto);
        assertThat(savedProducto).isEqualTo(producto);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        productoService.deleteById(id);
        verify(productoRepository, times(1)).deleteById(id);
    }
}