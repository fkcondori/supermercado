package uno.supermercado.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import uno.supermercado.model.Categoria;
import uno.supermercado.model.Producto;
import uno.supermercado.repository.CategoriaRepository;
import uno.supermercado.repository.ProductoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllCategorias() throws Exception {
        // Preparar datos
        Categoria categoria = new Categoria();
        categoria.setNombre("Categoria 1");
        categoria.setDescripcion("Descripcion 1");
        categoriaRepository.save(categoria);

        // Ejecutar y verificar
        mockMvc.perform(get("/api/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Lácteos"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetCategoriaById() throws Exception {
        // Ejecutar la petición GET para la categoría con ID 1
        mockMvc.perform(get("/api/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Leche Entera"))
                .andExpect(jsonPath("$[1].nombre").value("Queso Cheddar"));
    }

    @Test
    public void testCreateCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setNombre("New Categoria");
        categoria.setDescripcion("New Descripcion");

        mockMvc.perform(post("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("New Categoria"));
    }

    @Test
    public void testUpdateCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setNombre("Update Categoria");
        categoria.setDescripcion("Update Descripcion");
        categoria = categoriaRepository.save(categoria);

        categoria.setNombre("Updated Categoria");

        mockMvc.perform(put("/api/categorias/" + categoria.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Updated Categoria"));
    }

    @Test
    public void testDeleteCategoria() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setNombre("Delete Categoria");
        categoria.setDescripcion("Delete Descripcion");
        categoria = categoriaRepository.save(categoria);

        mockMvc.perform(delete("/api/categorias/" + categoria.getId()))
                .andExpect(status().isNoContent());

        assertThat(categoriaRepository.findById(categoria.getId())).isEmpty();
    }
}
