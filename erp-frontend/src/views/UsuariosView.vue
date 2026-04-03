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
  if (!nombre.value || !edad.value || !ciudad.value) {
    alert("Completa todos los campos")
    return
  }

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
  if (confirm("¿Eliminar usuario?")) {
    await eliminarUsuario(id)
    cargarUsuarios()
  }
}

onMounted(cargarUsuarios)
</script>

<template>
  <div class="page">
    <h1>Gestión de Usuarios</h1>

    <!-- FORMULARIO -->
    <div class="card">
      <h3>Nuevo usuario</h3>

      <div class="form">
        <input v-model="nombre" placeholder="Nombre" />
        <input v-model="edad" type="number" placeholder="Edad" />
        <input v-model="ciudad" placeholder="Ciudad" />

        <button @click="guardarUsuario">Guardar</button>
      </div>
    </div>

    <!-- TABLA -->
    <div class="card">
      <h3>Listado</h3>

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
</template>

<style scoped>
.page {
  padding: 20px;
  font-family: Arial;
}

h1 {
  margin-bottom: 20px;
}

/* CARD */
.card {
  background: rgba(255, 0, 0, 0.05);
  border-radius: 10px;
  padding: 20px;
  margin-bottom: 20px;
}

/* FORM */
.form {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
}

.form input {
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
}

.form button {
  background: #b30000;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 6px;
  cursor: pointer;
}

/* TABLA */
table {
  width: 100%;
  border-collapse: collapse;
}

th {
  background: #f5f5f5;
  text-align: left;
  padding: 10px;
}

td {
  padding: 10px;
  border-top: 1px solid #ddd;
}

.delete {
  background: red;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 6px;
  cursor: pointer;
}
</style>
