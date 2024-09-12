import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css';
import VMoney from 'v-money3';


import 'bootstrap/dist/css/bootstrap.min.css' // Importa el CSS de Bootstrap
import 'bootstrap'


const app = createApp(App)
app.use(VMoney);

app.use(router)

app.mount('#app')
