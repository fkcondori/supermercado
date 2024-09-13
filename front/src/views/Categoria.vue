<template>
  <div class="container-fluid">
    <div class="d-flex justify-content-center align-items-center">
      <h2 class="mb-4">Categorías</h2>
    </div>
    <!-- Botón para añadir una nueva categoría y abrir el modal -->
    <button @click="abrirModal" class="btn btn-success mb-3">Añadir</button>

    <!-- Contenedor con scroll para la tabla -->
    <div class="table-container">
      <table class="table table-hover">
        <thead class="thead-dark sticky-header">
          <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Descripción</th>
            <th scope="col">Creación</th>
            <th scope="col">Actualización</th>
            <th scope="col"></th>
            <th scope="col">Productos</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="categoria in categorias" :key="categoria.id">
            <td>{{ categoria.nombre }}</td>
            <td>{{ categoria.descripcion }}</td>
            <td>{{ formatDate(categoria.fechaCreacion) }}</td>
            <td>{{ formatDate(categoria.fechaActualizacion) }}</td>
            <td class="align-items-center">
              <div class="d-flex justify-content-center">
                <button @click="abrirModalEditar(categoria)" class="btn btn-peq btn-warning btn-sm me-2">
                <i class="fa-regular fa-pen-to-square"></i>
              </button>
              <button @click="confirmarEliminacion(categoria.id)" class="btn btn-peq btn-danger btn-sm">
                <i class="fa-solid fa-trash-can"></i>
              </button> 
              </div>
            </td>
            <td class="align-items-center">
              <div class="d-flex justify-content-center">
              <button @click="verProductos(categoria.id)" class="btn btn-peq btn-primary btn-sm"><i class="fa-regular fa-eye"></i></button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!-- Modal de confirmación de eliminación -->
    <div class="modal fade" tabindex="-1" :class="{ show: mostrarModalConfirmacion }" style="display: block;" v-if="mostrarModalConfirmacion" aria-labelledby="modalConfirmacionLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalConfirmacionLabel">Confirmar Eliminación</h5>
            <button type="button" class="btn-close" @click="cerrarModalConfirmacion" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>¿Estás seguro de que deseas eliminar esta categoría?</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cerrarModalConfirmacion">Cancelar</button>
            <button type="button" class="btn btn-danger" @click="eliminarCategoriaConfirmado">Eliminar</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Mensaje de error -->
    <div v-if="mostrarMensajeError" class="alert alert-danger alert-dismissible fade show" role="alert">
      {{ mensajeError }}
      <button type="button" class="btn-close" @click="cerrarMensajeError" aria-label="Close"></button>
    </div>

    <!-- Mensaje de éxito -->
    <div v-if="mostrarMensajeExito" class="alert alert-success alert-dismissible fade show" role="alert">
      {{ mensajeExito }}
      <button type="button" class="btn-close" @click="cerrarMensajeExito" aria-label="Close"></button>
    </div>

    <!-- Modal para añadir/editar categoría -->
    <div class="modal fade" tabindex="-1" :class="{ show: mostrarModal }" style="display: block;" v-if="mostrarModal" aria-labelledby="modalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">{{ editando ? 'Editar Categoría' : 'Añadir Categoría' }}</h5>
            <button type="button" class="btn-close" @click="cerrarModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <!-- Formulario dentro del modal -->
            <form>
              <div class="mb-3">
                <label for="nombreCategoria" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombreCategoria" v-model="nuevaCategoria.nombre" placeholder="Ingresa el nombre de la categoría">
              </div>
              <div class="mb-3">
                <label for="descripcionCategoria" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcionCategoria" v-model="nuevaCategoria.descripcion" placeholder="Ingresa una descripción"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cerrarModal">Cancelar</button>
            <button type="button" class="btn btn-primary" @click="editando ? actualizarCategoria() : guardarCategoria()">{{ editando ? 'Actualizar' : 'Guardar' }}</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default { 
  name: 'Categoria',
  data() {
    return {
      categorias: [], // Aquí se almacenarán las categorías obtenidas del backend
      productos: [], // Aquí se almacenarán los productos de una categoría específica
      mostrarModal: false, // Controla la visibilidad del modal
      mostrarMensajeExito: false, // Controla la visibilidad del mensaje de éxito
      mostrarModalConfirmacion: false, // Controla la visibilidad del modal de confirmación de eliminación
      mensajeExito: '', // Mensaje de éxito a mostrar
      mostrarMensajeError: false, // Controla la visibilidad del mensaje de error
      mensajeError: '', // Mensaje de error a mostrar
      editando: false, // Controla si estamos editando o añadiendo una categoría
      nuevaCategoria: {
        id: null,
        nombre: '',
        descripcion: '',
        fechaCreacion: null,
        fechaActualizacion: null
      },
    };
  },

  mounted() {
    this.getAllCategoria();
  },

  methods: {
    getAllCategoria() {
      axios.get('/api/categorias', { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          console.log('Datos recibidos:', response.data);
          this.categorias = response.data;
        })
        .catch(error => {
          console.error('Error al obtener categorías:', error);
        });
    },

    formatDate(date) {
      if (date) {
        return new Date(date).toLocaleDateString('es-ES'); // Ajusta el formato según tus necesidades
      }
      return '';
    },


    verProductos(categoriaId) {
    this.$router.push({ name: 'Productos', query: { categoriaId }});
  },

    abrirModal() {
      this.editando = false;
      this.nuevaCategoria = { id: null, nombre: '', descripcion: '' };
      this.mostrarModal = true;
    },
    cerrarModalConfirmacion() {
      this.mostrarModalConfirmacion = false;
    },
    confirmarEliminacion(categoriaId) {
      this.categoriaAEliminar = categoriaId;
      this.mostrarModalConfirmacion = true;
    },

    abrirModalEditar(categoria) {
      this.editando = true;
      this.nuevaCategoria = { ...categoria };
      this.mostrarModal = true;
    },

    cerrarModal() {
      this.mostrarModal = false;
    },

    cerrarModalProductos() {
      this.mostrarModalProductos = false;
    },

    cerrarMensajeExito() {
      this.mostrarMensajeExito = false;
    },

    guardarCategoria() {
      if (this.nuevaCategoria.nombre && this.nuevaCategoria.descripcion) {
        const fechaActual = new Date().toISOString().split('T')[0];
        this.nuevaCategoria.fechaCreacion = fechaActual;
        this.nuevaCategoria.fechaActualizacion = fechaActual;
        axios.post('/api/categorias', this.nuevaCategoria)
          .then(response => {
            console.log('Categoría creada:', response.data);
            this.getAllCategoria(); // Actualizar la lista después de añadir
            this.cerrarModal();
            this.mostrarMensajeExito = true;
            this.mensajeExito = 'Categoría agregada exitosamente.';
            setTimeout(() => {
              this.cerrarMensajeExito();
            }, 3000); // Ocultar el mensaje después de 3 segundos
          })
          .catch(error => {
            console.error('Error al crear la categoría:', error);
          });
      } else {
        alert('Por favor, rellena todos los campos.');
      }
    },

    actualizarCategoria() {
      if (this.nuevaCategoria.nombre && this.nuevaCategoria.descripcion) {
        this.nuevaCategoria.fechaActualizacion = new Date();
        axios.put(`/api/categorias/${this.nuevaCategoria.id}`, this.nuevaCategoria)
          .then(response => {
            this.getAllCategoria();
            console.log('Categoría actualizada:', response.data);
            this.cerrarModal();
            this.mostrarMensajeExito = true;
            this.mensajeExito = 'Categoría actualizada exitosamente.';
            setTimeout(() => {
              this.cerrarMensajeExito();
            }, 3000); // Ocultar el mensaje después de 3 segundos
          })
          .catch(error => {
            console.error('Error al actualizar la categoría:', error);
          });
      } else {
        alert('Por favor, rellena todos los campos.');
      }
    },

    eliminarCategoriaConfirmado() {
  if (this.categoriaAEliminar) {
    axios.delete(`/api/categorias/${this.categoriaAEliminar}`)
      .then(() => {
        this.getAllCategoria(); // Actualizar la lista después de eliminar
        this.cerrarModalConfirmacion();
        this.mostrarMensajeExito = true;
        this.mensajeExito = 'Categoría eliminada exitosamente.';
        setTimeout(() => {
          this.cerrarMensajeExito();
        }, 3000); // Ocultar el mensaje después de 3 segundos
      })
      .catch(error => {
        if (error.response) {
          // Verificar el código de estado y el mensaje del error
          if (error.response.status === 400) {
            this.mostrarMensajeError = true;
            this.mensajeError = 'No se puede eliminar la categoría porque tiene productos asociados.';
          } else if (error.response.status === 409) {
            this.mostrarMensajeError = true;
            this.mensajeError = 'Conflicto al eliminar la categoría. Verifique si tiene productos asociados.';
          } else {
            this.mostrarMensajeError = true;
            this.mensajeError = 'Error inesperado al eliminar la categoría. Inténtelo nuevamente.';
          }
        } else {
          this.mostrarMensajeError = true;
          this.mensajeError = 'Error de red al eliminar la categoría.';
        }
        this.cerrarModalConfirmacion();
      });
  }
},

    cerrarMensajeError() {
      this.mostrarMensajeError = false;
    },
  
  }
};
</script>

<style scoped>
.table-hover tbody tr:hover {
  background-color: #f0f0f0;
}

.table-container {
  max-width: 100%; /* Hace que la tabla se extienda al máximo ancho del contenedor */
  max-height: 60vh; /* Ajusta la altura máxima de la tabla */
  overflow: auto; /* Permite el desplazamiento si el contenido excede el alto máximo */
  margin-bottom: 20px;
}

.table {
  width: 100%; /* Hace que la tabla ocupe todo el ancho del contenedor */
  border-collapse: collapse; /* Mejora la visualización de bordes */
}

.sticky-header th {
  position: sticky;
  top: 0;
  background-color: #3c4146; /* Color de fondo del encabezado de la tabla*/
  border-bottom: 2px solid #dee2e6; /* Línea divisora del encabezado */
  color: white; /* Color del texto del encabezado */
  z-index: 1; /* Asegura que el encabezado esté por encima del contenido */
  border-right: 1px solid #dee2e6;
  text-align: center;
}

@media (max-width: 768px) {
  .table-container {
    max-height: 60vh; /* Ajusta la altura máxima en pantallas más pequeñas */
  }
}

.btn {
  margin: 0.1rem;
  width: 110px; /* Asegura que todos los botones tengan el mismo tamaño */
  display: inline-flex; /* Asegura que el contenido del botón esté centrado */
  justify-content: center; /* Centra el texto dentro del botón */
}

.btn.btn-peq {
  margin: 0.1rem;
  width: 30px !important; /* Asegura que todos los botones tengan el mismo tamaño */
  display: inline-flex; /* Asegura que el contenido del botón esté centrado */
  justify-content: center; /* Centra el texto dentro del botón */
}

.modal {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1050;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-dialog {
  margin-top: 10%;
}

.alert {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1060;
}
</style>