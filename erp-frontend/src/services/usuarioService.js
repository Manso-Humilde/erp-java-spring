const API_URL = "http://localhost:8080/api/usuarios";

export const obtenerUsuarios = async () => {
  const res = await fetch(API_URL);
  return await res.json();
};

export const crearUsuario = async (usuario) => {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(usuario)
  });
  return await res.json();
};

export const eliminarUsuario = async (id) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE"
  });
};
