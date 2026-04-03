<script setup>
import { ref, onMounted } from 'vue'
import { obtenerUsuarios, crearUsuario, eliminarUsuario } from './services/usuarioService'

const usuarios = ref([])
const nombre = ref("")
const edad = ref("")
const ciudad = ref("")

const cargarUsuarios = async () => {
  usuarios.value = await obtenerUsuarios()
}

const guardarUsuario = async () => {
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

onMounted(() => {
  cargarUsuarios()
})
</script>

<template>
  <h1>ERP - Usuarios</h1>

  <input v-model="nombre" placeholder="Nombre" />
  <input v-model="edad" placeholder="Edad" />
  <input v-model="ciudad" placeholder="Ciudad" />

  <button @click="guardarUsuario">Guardar</button>

  <ul>
    <li v-for="u in usuarios" :key="u.id">
      {{ u.nombre }} - {{ u.edad }} - {{ u.ciudad }}
      <button @click="borrarUsuario(u.id)">❌</button>
    </li>
  </ul>
</template>
