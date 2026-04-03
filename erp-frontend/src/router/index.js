import { createRouter, createWebHistory } from 'vue-router'
import UsuariosView from '../views/UsuariosView.vue'

const routes = [
    {
        path: '/',
        component: UsuariosView
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
