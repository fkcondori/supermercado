<template>
    <div class="container mt-5">
      <h2 class="mb-4">Nuestros Productos</h2>
      
      <div class="row">
        <div class="col-md-4" v-for="producto in productos" :key="producto.id">
          <div class="card mb-4" style="width: 18rem;">
            <img :src="producto.imagenUrl" class="card-img-top" alt="Imagen del producto">
            <div class="card-body">
              <h5 class="card-title">{{ producto.nombre }}</h5>
              <p class="card-text">{{ producto.descripcion }}</p>
              <p class="card-text"><strong>Precio:</strong> ${{ producto.precio }}</p>
              <a href="#" class="btn btn-primary">Comprar ahora</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'Productos',
    data() {
      return {
        productos: [], // Aquí se almacenarán los productos obtenidos del backend
      };
    },
    mounted() {
      this.obtenerProductos();
    },
    methods: {
      obtenerProductos() {
        // Aquí haces la llamada al backend Spring Boot para obtener los productos
        axios.get('http://localhost:8080/api/productos')
          .then(response => {
            this.productos = response.data;
          })
          .catch(error => {
            console.error('Error al obtener productos:', error);
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .card-img-top {
    height: 200px;
    object-fit: cover; /* Ajusta la imagen sin deformarla */
  }
  </style>
  