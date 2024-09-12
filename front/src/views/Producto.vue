<template>
  <div class="container-fuid mt-5">
    <h2 class="mb-4">Productos</h2>
    <button @click="abrirModalAdd" class="btn btn-success mb-3">Añadir</button>
    
    <div class="row">
      <div class="col-md-4" v-for="producto in productos" :key="producto.id">
        <div class="card mb-4" style="width: 18rem;">
          <img :src="getImageApi(producto.idProducto)" class="card-img-top" alt="Imagen del producto">
          <div class="card-body">
            <h5 class="card-title">{{ producto.nombre }}</h5>
            <p class="card-text">{{ producto.descripcion }}</p>
            <p class="card-text">{{ producto.origenProducto }}</p>
            <p class="card-text">{{ producto.marca }}</p>
            <p class="card-text"><strong>Precio:</strong> {{ producto.precio }}€</p>
            <button @click="abrirModalEdit" class="btn btn-warning mb-3">Editar</button>
          </div>
        </div>
      </div>
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
    };
  },
  mounted() {
    this.getAllProductos();
  },
  methods: {
    getAllProductos() {
      axios.get('/api/productos', { headers: { 'Cache-Control': 'no-cache' } })
        .then(response => {
          console.log('Datos recibidos:', response.data);
          this.productos = response.data; // Asigna los datos recibidos a productos
        })
        .catch(error => {
          console.error('Error al obtener productos:', error);
        })
    },
    getImageUrl(imagen) { // Devuelve la URL de la imagen de la carpeta /public/images
      return `/images/${imagen}`;
    },
    getImageApi(idProducto) { // Devuelve la URL de la imagen de la carpeta de imágenes del backend a través de la API
      return `/api/productos/${idProducto}/image`;
    },
  },
};
</script>

<style scoped>
.card-img-top {
  height: 200px;
  object-fit: cover; /* Ajusta la imagen sin deformarla */
}
.btn {
    margin: 0.1rem;
    width: 100px; /* Asegura que todos los botones tengan el mismo tamaño */
    display: inline-flex; /* Asegura que el contenido del botón esté centrado */
    justify-content: center; /* Centra el texto dentro del botón */
  }
</style>