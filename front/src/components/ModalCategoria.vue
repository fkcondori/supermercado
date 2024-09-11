<template>
    <div v-if="show" class="modal fade show" tabindex="-1" role="dialog" style="display: block;">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Añadir Categoría</h5>
            <button type="button" class="btn-close" @click="cerrarModal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <!-- Formulario dentro del modal -->
            <form @submit.prevent="guardarCategoria">
              <div class="mb-3">
                <label for="nombreCategoria" class="form-label">Nombre de la Categoría</label>
                <input type="text" class="form-control" id="nombreCategoria" v-model="nuevaCategoria.nombre" placeholder="Ingresa el nombre de la categoría" required>
              </div>
              <div class="mb-3">
                <label for="descripcionCategoria" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcionCategoria" v-model="nuevaCategoria.descripcion" placeholder="Ingresa una descripción" required></textarea>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="cerrarModal">Cerrar</button>
                <button type="submit" class="btn btn-primary">Guardar</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ModalCategoria',
    props: {
      show: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        nuevaCategoria: {
          nombre: '',
          descripcion: ''
        }
      };
    },
    methods: {
      cerrarModal() {
        this.$emit('update:show', false); // Emite un evento para cerrar el modal
      },
      guardarCategoria() {
        if (this.nuevaCategoria.nombre && this.nuevaCategoria.descripcion) {
          console.log('Categoría guardada:', this.nuevaCategoria);
          // Aquí podrías hacer una llamada a la API para guardar la categoría
          this.cerrarModal(); // Cierra el modal después de guardar
        } else {
          alert('Por favor, rellena todos los campos.');
        }
      }
    }
  };
  </script>
  
  <style scoped>
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
  