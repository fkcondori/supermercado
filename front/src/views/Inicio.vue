<template>
  <div class="container-fluid inicio-container d-flex justify-content-center align-items-center">
    <div class="text-center bg-dark text-white p-5 rounded overlay-text">
      <h1 class="display-4">Bienvenidos</h1>
      <p class="lead">Plataforma de Gestión del Supermercado CIC</p>
    </div>
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="/supermercado.png" class="d-block w-100 " alt="Supermercado 1">
        </div>
        <div class="carousel-item">
          <img src="/supermercado3.png" class="d-block w-100 " alt="Supermercado 2">
        </div>
        <div class="carousel-item">
          <img src="/supermercado.png" class="d-block w-100 " alt="Supermercado 3">
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</template>
  
  <script>
  export default {
    name: 'Inicio'
  };
  </script>
  
  <style scoped>
  /* Asegura que html y body ocupen el 100% de la pantalla */
  html, body {
    height: 100vh;
    margin: 0;
  }
  
  /* Asegura que el contenedor ocupe el 100% del alto de la pantalla */
  .inicio-container {
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .overlay-text {
    position: absolute;
    z-index: 10;
  }


  </style>