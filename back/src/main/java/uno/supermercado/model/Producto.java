package uno.supermercado.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(max = 150, message = "El nombre del producto no puede tener más de 150 caracteres")
    @Column(nullable = false, length = 150, unique = true)
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    @Column(nullable = true, length = 255)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "El precio debe ser mayor o igual a 0.01")
    @DecimalMax(value = "10000.00", message = "El precio no puede exceder de 10,000")
    @Column(nullable = false)
    private Double precio;

    @NotBlank(message = "La imagen no puede estar vacía")
    @Pattern(regexp = ".*\\.(jpg|png|jpeg)", message = "La imagen debe tener una extensión válida (.jpg, .png, .jpeg)")
    @Column(nullable = false)
    private String imagen;

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(max = 30, message = "La marca no puede tener más de 30 caracteres")
    @Column(nullable = false, length = 30)
    private String marca;

    @Size(max = 30, message = "El origen no puede tener más de 30 caracteres")
    @Column(nullable = true, length = 30)
    private String origenProducto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    @NotNull(message = "El producto debe estar asociado a una categoría")
    @JsonBackReference
    private Categoria categoria;

}
