<template>
  <aside class="sidebar">
    <h2>ERP</h2>
    <nav>
      <a href="#">Usuarios</a>
      <a href="#">Productos</a>
      <a href="#">Pedidos</a>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  width: 220px;
  background: rgba(180, 0, 0, 0.9);
  color: white;
  padding: 20px;
}

.sidebar h2 {
  margin-bottom: 20px;
}

.sidebar a {
  display: block;
  color: white;
  margin: 10px 0;
  text-decoration: none;
  transition: 0.2s;
}

.sidebar a:hover {
  opacity: 0.7;
}

/* MOBILE */
@media (max-width: 768px) {
  .sidebar {
    width: 100%;
    display: flex;
    justify-content: space-around;
  }
}
</style>
