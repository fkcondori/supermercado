package uno.supermercado.categoria;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uno.supermercado.controller.CategoriaController;
import uno.supermercado.model.Categoria;
import uno.supermercado.repository.*;
import uno.supermercado.service.CategoriaService;

public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    public CategoriaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        categoriaService.findAll();
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(new Categoria()));
        categoriaService.findById(id);
        verify(categoriaRepository, times(1)).findById(id);
    }

    @Test
    public void testSave() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        Categoria savedCategoria = categoriaService.save(categoria);
        assertThat(savedCategoria).isEqualTo(categoria);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        categoriaService.deleteById(id);
        verify(categoriaRepository, times(1)).deleteById(id);
    }
}