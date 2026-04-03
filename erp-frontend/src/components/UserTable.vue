<script setup>
import { ref, onMounted } from 'vue'
import { obtenerUsuarios, crearUsuario, eliminarUsuario } from '../services/usuarioService'

const usuarios = ref([])
const nombre = ref("")
const edad = ref("")
const ciudad = ref("")

const cargarUsuarios = async () => {
  usuarios.value = await obtenerUsuarios()
}

const guardarUsuario = async () => {
  if (!nombre.value || !edad.value || !ciudad.value) return

  await crearUsuario({
    nombre: nombre.value,
    edad: parseInt(edad.value),
    ciudad: ciudad.value
  })

  nombre.value = ""
  edad.value = ""
  ciudad.value = ""

  cargarUsuarios()
}

const borrarUsuario = async (id) => {
  await eliminarUsuario(id)
  cargarUsuarios()
}

onMounted(cargarUsuarios)
</script>

<template>
  <div class="page">
    <!-- FORM -->
    <div class="card form">
      <h3>Nuevo usuario</h3>

      <div class="inputs">
        <input v-model="nombre" placeholder="Nombre" />
        <input v-model="edad" type="number" placeholder="Edad" />
        <input v-model="ciudad" placeholder="Ciudad" />
        <button @click="guardarUsuario">Guardar</button>
      </div>
    </div>

    <!-- TABLA -->
    <div class="card">
      <h3>Usuarios</h3>

      <div class="table-container">
        <table>
          <thead>
          <tr>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Ciudad</th>
            <th></th>
          </tr>
          </thead>

          <tbody>
          <tr v-for="u in usuarios" :key="u.id">
            <td>{{ u.nombre }}</td>
            <td>{{ u.edad }}</td>
            <td>{{ u.ciudad }}</td>
            <td>
              <button class="delete" @click="borrarUsuario(u.id)">
                Eliminar
              </button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
.page {
  padding: 20px;
}

/* CARD */
.card {
  background: white;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.08);
}

/* FORM */
.inputs {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}

.inputs input {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  flex: 1;
}

.inputs button {
  background: #b30000;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 8px;
  cursor: pointer;
}

/* TABLA */
.table-container {
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th {
  text-align: left;
  padding: 10px;
  background: #f9f9f9;
}

td {
  padding: 10px;
  border-top: 1px solid #eee;
}

/* BOTÓN */
.delete {
  background: #ff4d4d;
  border: none;
  padding: 6px 10px;
  color: white;
  border-radius: 6px;
  cursor: pointer;
}

/* MOBILE */
@media (max-width: 768px) {
  .inputs {
    flex-direction: column;
  }
}
</style>
