<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header class="p-3 text-bg-dark">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li class="nav-item px-2">
            <RouterLink to="/" class="nav-link">Inicio</RouterLink>
          </li>
          <li class="nav-item px-2">
            <RouterLink to="/categoria" class="nav-link">Categorías</RouterLink>
          </li>
          <li class="nav-item px-2">
            <RouterLink to="/productos" class="nav-link">Productos</RouterLink>
          </li>
        </ul>
      </div>
    </div>
  </header>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <main>
          <!-- Contenido dinámico -->
          <RouterView />
        </main>
      </div>
    </div>
  </div>
</template>

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

header {
  line-height: 1.5;
  width: 100%; /* Asegura que el header ocupe todo el ancho */
  position: fixed; /* Para que se mantenga en la parte superior */
  top: 0;
  left: 0;
  z-index: 1000; /* Para que esté por encima del contenido */
  background: linear-gradient(90deg, #343a40, #495057); /* Gradiente de fondo */
  padding: 1rem 0; /* Ajusta el padding */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Sombra para darle profundidad */
}

.nav-link {
  color: #ffffff; /* Color de los enlaces */
  font-size: 1rem; /* Tamaño de fuente */
  font-weight: 500; /* Peso de la fuente */
  transition: color 0.3s ease; /* Transición para el efecto hover */
}

.nav-link:hover {
  color: #adb5bd; /* Color al pasar el ratón */
  text-decoration: underline; /* Subrayado al pasar el ratón */
}

.nav-item {
  margin: 0 0.5rem; /* Espaciado entre los elementos del menú */
}

main {
  width: 100%;
  padding: 1rem; /* Agrega un padding para evitar que el contenido toque los bordes */
}

@media (min-width: 1024px) {
  header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding-right: 2rem; /* Asegura un buen espacio a la derecha */
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;
    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>