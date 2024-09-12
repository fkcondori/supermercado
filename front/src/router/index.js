import { createRouter, createWebHistory } from 'vue-router';
import Inicio from '../views/Inicio.vue';
import Categoria from "../views/Categoria.vue";
import Producto from "../views/Producto.vue";

const routes = [
  {
    path: "/",
    name: "Inicio",
    component: Inicio,
  },
  {
    path: "/categoria",
    name: "Categoria",
    component: Categoria,
  },
  {
    path: "/productos",
    //path: "/productos",
    name: "Productos",
    component: Producto,
    props: true
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;