<template>
  <div class="contenedor container-fluid">
    <div> 
      <div class="d-flex justify-content-center align-items-center">
        <h2 class="mb-4">Productos</h2>
      </div>
      <button @click="abrirModalAdd" class="btn btn-success mb-3">Añadir</button>

      <!-- Desplegable de Categorías -->
      <div class="mb-4">
        <select v-model="categoriaId" @change="filterByCategory" class="form-select">
          <option value="-1">Mostrar Todos</option>
          <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">
            {{ categoria.nombre }}
          </option>
        </select>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-4" v-for="producto in productos" :key="producto.idProducto">
        <div class="card mb-4" style="width: 18rem;">
          <img :src="getImageApi(producto.idProducto)" class="card-img-top" alt="Imagen del producto">
          <div class="card-body">
            <h5 class="card-title text-center">{{ producto.nombre }}</h5>
            <p class="card-text" :title="producto.descripcion">
              <strong>Descripción:</strong> 
              <span class="descripcion">{{ producto.descripcion }}</span>
            </p>
            <p class="card-text"><strong>Origen:</strong> {{ producto.origenProducto }}</p>
            <p class="card-text"><strong>Marca:</strong> {{ producto.marca }}</p>
            <p class="card-text"><strong>Precio:</strong> {{ producto.precio }}€</p>
            <div class="d-flex justify-content-end"> 
              <button @click="abrirModalEdit(producto)" class="btn btn-peq btn-warning mb-3 me-2"><i class="fa-regular fa-pen-to-square"></i></button>
              <button @click="abrirModalEliminar(producto.idProducto)" class="btn btn-peq btn-danger mb-3"><i class="fa-solid fa-trash-can"></i></button> 
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal para añadir/editar producto -->
    <div class="modal fade" tabindex="-1" :class="{ show: mostrarModal }" style="display: block;" v-if="mostrarModal" aria-labelledby="modalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalLabel">{{ editando ? 'Editar Producto' : 'Añadir Producto' }}</h5>
            <button type="button" class="btn-close" @click="cerrarModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <!-- Formulario dentro del modal -->
            <form @submit.prevent="editando ? actualizarProducto() : guardarProducto()">
              <div class="mb-3">
                <label for="nombreProducto" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombreProducto" v-model="nuevoProducto.nombre" placeholder="Ingresa el nombre del producto">
              </div>
              <div class="mb-3">
                <label for="descripcionProducto" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcionProducto" v-model="nuevoProducto.descripcion" placeholder="Ingresa una descripción"></textarea>
              </div>
              <div class="mb-3">
                <label for="origenProducto" class="form-label">Origen</label>
                <input type="text" class="form-control" id="origenProducto" v-model="nuevoProducto.origenProducto" placeholder="Ingresa el origen del producto">
              </div>
              <div class="mb-3">
                <label for="marcaProducto" class="form-label">Marca</label>
                <input type="text" class="form-control" id="marcaProducto" v-model="nuevoProducto.marca" placeholder="Ingresa la marca del producto">
              </div>
              <div class="mb-3">
                <label for="precioProducto" class="form-label">Precio</label>
                <input type="text" class="form-control" id="precioProducto" v-model="nuevoProducto.precio" v-money="money" placeholder="Ingresa el precio del producto">
              </div>
              <div class="mb-3">
                <label for="categoriaProducto" class="form-label">Categoría</label>
                <select class="form-select" id="categoriaProducto" v-model="nuevoProducto.categoriaId">
                  <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">
                    {{ categoria.nombre }}
                  </option>
                </select>
              </div>
              <div class="mb-3">
                <label for="imagenProducto" class="form-label">Imagen</label>
                <input type="file" class="form-control" id="imagenProducto" @change="handleFileUpload">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="cerrarModal">Cancelar</button>
                <button type="submit" class="btn btn-primary" @click="editando?actualizarProducto():guardarProducto()">{{ editando ? 'Actualizar' : 'Guardar' }}</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal de confirmación para eliminar producto -->
    <div class="modal fade" tabindex="-1" :class="{ show: mostrarModalEliminar }" style="display: block;" v-if="mostrarModalEliminar" aria-labelledby="modalEliminarLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modalEliminarLabel">Confirmar Eliminación</h5>
            <button type="button" class="btn-close" @click="cerrarModalEliminar" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>¿Estás seguro de que deseas eliminar este producto?</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cerrarModalEliminar">Cancelar</button>
            <button type="button" class="btn btn-danger" @click="confirmarEliminarProducto">Eliminar</button>
          </div>
        </div>
      </div>
    </div>

    
    <!-- Mensaje de éxito -->
    <div v-if="mostrarMensajeExito" class="alert alert-success alert-dismissible fade show" role="alert">
      {{ mensajeExito }}
      <button type="button" class="btn-close" @click="cerrarMensajeExito" aria-label="Close"></button>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'Producto',
  data() {
    return {
      productos: [], // Aquí se almacenarán los productos obtenidos del backend
      categorias: [], // Aquí se almacenarán las categorías obtenidas del backend
      categoriaId: '-1', // Se selecciona por defecto mostrar todos los productos
      mostrarModal: false, // Controla la visibilidad del modal
      mostrarModalEliminar: false, // Controla la visibilidad del modal de eliminación
      mostrarMensajeExito: false, // Controla la visibilidad del mensaje de éxito
      mensajeExito: '', // Mensaje de éxito a mostrar
      editando: false, // Controla si estamos editando o añadiendo un producto
      imagenProducto: null, // Almacena la imagen seleccionada
      nuevoProducto: {
        idProducto: null,
        nombre: '',
        descripcion: '',
        origenProducto: '',
        marca: '',
        precio: '',
      },
      productoOriginal: null // Añadimos la propiedad para guardar el producto original
    };
  },
  money: {
        decimal: ',',
        thousands: '.',
        prefix: '',
        suffix: ' €',
        precision: 2
      },

  mounted() {
    console.log('CategoriaId desde la URL:', this.$route.query.categoriaId);
    this.categoriaId = this.$route.query.categoriaId || '-1';
    this.getAllProductos();
    this.getCategorias();
  },

  methods: {
    getAllProductos() {
      const categoriaId = this.$route.query.categoriaId;

      if (categoriaId) {
        axios.get(`/api/categorias/${categoriaId}/productos`, { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            console.log('Datos de productos filtrados por categoría recibidos:', response.data);
            this.productos = response.data; // Asigna los datos recibidos a productos
          })
          .catch(error => {
            console.error('Error al obtener productos filtrados por categoría:', error);
          });
      } else {
        axios.get('/api/productos', { headers: { 'Cache-Control': 'no-cache' } })
          .then(response => {
            console.log('Datos de productos recibidos:', response.data);
            this.productos = response.data; // Asigna los datos recibidos a productos
          })
          .catch(error => {
            console.error('Error al obtener productos:', error);
          });
      }
    },
    getCategorias() {
      axios.get('/api/categorias', { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          console.log('Datos de categorías recibidos:', response.data);
          this.categorias = response.data; // Asigna los datos recibidos a categorías
        })
        .catch(error => {
          console.error('Error al obtener categorías:', error);
        });
    },
    getImageApi(idProducto) {
      return `/api/productos/${idProducto}/image`;
    },
    filterByCategory() {
      const url = this.categoriaId === '-1' ? '/api/productos' : `/api/categorias/${this.categoriaId}/productos`;
      axios.get(url, { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          this.productos = response.data; // Asigna los datos recibidos a productos
        })
        .catch(error => {
          console.error('Error al obtener productos:', error);
        });
    },
    abrirModalAdd() {
      this.editando = false;
      this.nuevoProducto = { idProducto: null, nombre: '', descripcion: '', origenProducto: '', marca: '', precio: 0 };
      this.mostrarModal = true;
    },
    abrirModalEdit(producto) {
      this.editando = true;
      this.nuevoProducto = { ...producto };
      this.productoOriginal = { ...producto };
      this.mostrarModal = true;
    },
    abrirModalEliminar(idProducto) {
    this.productoAEliminar = idProducto; // Guardamos el ID del producto que queremos eliminar
    this.mostrarModalEliminar = true; // Mostramos el modal de confirmación
  },
    cerrarModal() {
      this.mostrarModal = false;
    },
    cerrarMensajeExito() {
      this.mostrarMensajeExito = false;
    },
    guardarProducto() {
      // Crea un objeto FormData
      const formData = new FormData();
      
      // Añade los datos del producto
      formData.append('nombre', this.nuevoProducto.nombre);
      formData.append('descripcion', this.nuevoProducto.descripcion);
      formData.append('origenProducto', this.nuevoProducto.origenProducto);
      formData.append('marca', this.nuevoProducto.marca);
      formData.append('precio', this.nuevoProducto.precio);
      formData.append('categoriaId', this.nuevoProducto.categoriaId);
      
      // Añade el archivo de imagen si existe
      if (this.imagenProducto) {
        formData.append('imagen', this.imagenProducto);
      }
      
      // Envía la solicitud POST con FormData
      axios.post('/api/productos', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        console.log('Producto creado:', response.data);
        this.getAllProductos(); // Actualizar la lista después de añadir
        this.cerrarModal();
        this.mostrarMensajeExito = true;
        this.mensajeExito = 'Producto agregado exitosamente.';
        setTimeout(() => {
          this.cerrarMensajeExito();
        }, 3000); // Ocultar el mensaje después de 3 segundos
      })
      .catch(error => {
        console.error('Error al crear el producto:', error);
      });
    },
  
  handleFileUpload(event) {
    // Almacena el archivo seleccionado en la variable imagenProducto
    this.imagenProducto = event.target.files[0];
  },


  actualizarProducto() {
    // Crea un objeto FormData
    const formData = new FormData();

    // Verifica si se ha proporcionado una nueva categoría
    let categoria = this.nuevoProducto.categoriaId !== undefined 
  ? this.nuevoProducto.categoriaId 
  : this.productoOriginal.categoriaId;
    console.log('Categoría seleccionada:', categoria);
    // Añade los datos actualizados del producto
    formData.append('id', this.nuevoProducto.idProducto);
    formData.append('nombre', this.nuevoProducto.nombre);
    formData.append('descripcion', this.nuevoProducto.descripcion);
    formData.append('origenProducto', this.nuevoProducto.origenProducto);
    formData.append('marca', this.nuevoProducto.marca);
    formData.append('precio', this.nuevoProducto.precio);
    formData.append('categoriaId', categoria);

    // Añade el archivo de imagen si existe
    if (this.imagenProducto) {
      formData.append('imagen', this.imagenProducto);
    }

    // Envía la solicitud PUT con FormData
    axios.put(`/api/productos/${this.nuevoProducto.idProducto}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then(response => {
      console.log('Producto actualizado:', response.data);
      this.getAllProductos(); // Actualizar la lista después de editar
      this.cerrarModal();
      this.mostrarMensajeExito = true;
      this.mensajeExito = 'Producto actualizado exitosamente.';
      setTimeout(() => {
        this.cerrarMensajeExito();
      }, 3000); // Ocultar el mensaje después de 3 segundos
    })
    .catch(error => {
      console.error('Error al actualizar el producto:', error);
    });
  },
  confirmarEliminarProducto() {
    axios.delete(`/api/productos/${this.productoAEliminar}`)
      .then(() => {
        this.getAllProductos(); // Actualizar la lista después de eliminar
        this.mostrarMensajeExito = true;
        this.mensajeExito = 'Producto eliminado exitosamente.';
        setTimeout(() => {
          this.cerrarMensajeExito();
        }, 3000); // Ocultar el mensaje después de 3 segundos
      })
      .catch(error => {
        console.error('Error al eliminar producto:', error);
      })
      .finally(() => {
        this.cerrarModalEliminar(); // Cerrar el modal de confirmación después de eliminar
      });
  },
  cerrarModalEliminar() {
    this.mostrarModalEliminar = false;
    this.productoAEliminar = null; // Limpiamos el producto a eliminar
  }
}
};
</script>

<style scoped>


.scroll-container {
  display: flex;
  align-items: center;
  overflow: hidden;
  position: relative;
  width: 100%; /* Ajusta el ancho del contenedor */
}

.btn {
  margin: 0.1rem;
  width: 100px; /* Asegura que todos los botones tengan el mismo tamaño */
  display: inline-flex; /* Asegura que el contenido del botón esté centrado */
  justify-content: center; /* Centra el texto dentro del botón */
}

.btn.btn-peq {
  margin: 0.1rem;
  width: 30px !important; /* Asegura que todos los botones tengan el mismo tamaño */
  display: inline-flex; /* Asegura que el contenido del botón esté centrado */
  justify-content: center; /* Centra el texto dentro del botón */
}

.descripcion {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
}

.form-select {
  max-width: 300px;
  margin-bottom: 20px;
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
.contenedor {
  margin-top: 11vh;
}
</style>
