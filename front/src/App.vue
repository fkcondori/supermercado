<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header class="p-3 text-bg-dark">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li class="nav-item px-2 text-secondary">
            <RouterLink to="/" class="active">Inicio</RouterLink>
          </li>
          <li class="nav-item px-2 text-white">
            <RouterLink to="/categoria" class="active">Categorías</RouterLink>
          </li>
          <li class="nav-item px-2 text-white">
            <RouterLink to="/producto" class="active">Productos</RouterLink>
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
main {
  display: block !important;
}

header {
  line-height: 1.5;
  width: 100%; /* Asegura que el header ocupe todo el ancho */
  position: fixed; /* Para que se mantenga en la parte superior */
  top: 0;
  left: 0;
  z-index: 1000; /* Para que esté por encima del contenido */
  background-color: #343a40; /* Color de fondo del header */
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

main {
  margin-top: 6rem; /* Ajusta el margen superior para evitar el header */
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
