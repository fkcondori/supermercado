<template>
    <div class="container-fluid mt-5">
      <h2 class="mb-4">Categorías</h2>
  
      <!-- Botón para añadir una nueva categoría y abrir el modal -->
      <!-- <button @click="abrirModal" class="btn btn-success mb-3"><i class="fa-solid fa-plus"></i></button> -->
      <button @click="abrirModal" class="btn btn-success mb-3">Añadir</button>
  
      <!-- Contenedor con scroll para la tabla -->
      <div class="table-container">
        <table class="table table-hover">
          <thead class="thead-dark">
            <tr>
              <th scope="col">Nombre</th>
              <th scope="col">Descripción</th>
              <th scope="col">Fecha de alta</th>
              <th scope="col">Fecha de actualización</th>
              <th scope="col">Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="categoria in categorias" :key="categoria.id">
              <td>{{ categoria.nombre }}</td>
              <td>{{ categoria.descripcion }}</td>
              <td>{{ formatDate(categoria.fechaCreacion) }}</td>
              <td>{{ formatDate(categoria.fechaActualizacion) }}</td>
              <td class="d-flex">
                <button @click="verProductos(categoria.id)" class="btn btn-peq btn-primary btn-sm me-2"><i class="fa-regular fa-eye"></i></button>
                <button @click="editarCategoria(categoria.id)" class="btn btn-peq btn-warning btn-sm me-2">
                  <i class="fa-regular fa-pen-to-square"></i>
                </button>
                <button @click="eliminarCategoria(categoria.id)" class="btn btn-peq btn-danger btn-sm">
                  <i class="fa-solid fa-trash-can"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
  
      <!-- Modal para añadir categoría -->
      <div class="modal fade" tabindex="-1" :class="{ show: mostrarModal }" style="display: block;" v-if="mostrarModal" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="modalLabel">Añadir Categoría</h5>
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
              <button type="button" class="btn btn-primary" @click="guardarCategoria">Guardar</button>
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
        mostrarModal: false, // Controla la visibilidad del modal
        nuevaCategoria: {
          nombre: '',
          descripcion: '',
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
        this.$router.push(`/productos/${categoriaId}`);
      },
  
      editarCategoria(categoriaId) {
        this.$router.push(`/editar-categoria/${categoriaId}`);
      },
  
      eliminarCategoria(categoriaId) {
        if (confirm('¿Estás seguro de que deseas eliminar esta categoría?')) {
          axios.delete(`/api/categorias/${categoriaId}`)
            .then(() => {
              this.getAllCategoria(); // Actualizar la lista después de eliminar
              alert('Categoría eliminada exitosamente.');
            })
            .catch(error => {
              console.error('Error al eliminar categoría:', error);
            });
        }
      },
  
      anadirCategoria() {
        this.$router.push('/anadir-categoria');
      },
  
      abrirModal() {
        this.mostrarModal = true;
      },
  
      cerrarModal() {
        this.mostrarModal = false;
      },
  
      guardarCategoria() {
        if (this.nuevaCategoria.nombre && this.nuevaCategoria.descripcion) {
          console.log('Categoría guardada:', this.nuevaCategoria);
          // Aquí podrías hacer una llamada a la API para guardar la categoría
          this.cerrarModal();
        } else {
          alert('Por favor, rellena todos los campos.');
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .table-hover tbody tr:hover {
    background-color: #f0f0f0;
  }
  
  .table-container {
    max-width: 100%; /* Hace que la tabla se extienda al máximo ancho del contenedor */
    max-height: 80vh; /* Ajusta la altura máxima según tus necesidades */
    overflow: auto; /* Permite el desplazamiento si el contenido excede el alto máximo */
    margin-bottom: 20px;
  }
  
  .table {
    width: 100%; /* Hace que la tabla ocupe todo el ancho del contenedor */
    border-collapse: collapse; /* Mejora la visualización de bordes */
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
  </style>
  