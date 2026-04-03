package com.haciendamanso.erp;

import com.haciendamanso.erp.dao.UsuarioRepository;
import com.haciendamanso.erp.model.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Scanner;

@SpringBootApplication
public class ErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

	@Bean
	public CommandLineRunner ejecutarMenu(UsuarioRepository usuarioRepository) {
		return args -> {
			Scanner sc = new Scanner(System.in);
			boolean ejecutar = true;

			while (ejecutar) {
				System.out.println("=== ERP HACIENDA MANSO Y HUMILDE ===");
				System.out.println("1. Añadir usuario");
				System.out.println("2. Mostrar usuarios");
				System.out.println("3. Buscar usuario por ID");
				System.out.println("4. Eliminar usuario por ID");
				System.out.println("5. Actualizar usuario");
				System.out.println("6. Salir");
				System.out.print("Selecciona una opción: ");

				String opcion = sc.nextLine();
				System.out.println();

				switch (opcion) {
					case "1":
						System.out.print("Nombre: ");
						String nombre = sc.nextLine();
						System.out.print("Edad: ");
						int edad = Integer.parseInt(sc.nextLine());
						System.out.print("Ciudad: ");
						String ciudad = sc.nextLine();

						usuarioRepository.save(new Usuario(0, nombre, edad, ciudad));
						System.out.println("✅ Usuario añadido correctamente.\n");
						break;

					case "2":
						usuarioRepository.findAll().forEach(System.out::println);
						System.out.println();
						break;

					case "3":
						System.out.print("ID: ");
						int idBuscar = Integer.parseInt(sc.nextLine());
						usuarioRepository.findById(idBuscar)
								.ifPresentOrElse(
										System.out::println,
										() -> System.out.println("❌ No existe un usuario con ese ID.")
								);
						System.out.println();
						break;

					case "4":
						System.out.print("ID a eliminar: ");
						int idEliminar = Integer.parseInt(sc.nextLine());
						if (usuarioRepository.existsById(idEliminar)) {
							usuarioRepository.deleteById(idEliminar);
							System.out.println("✅ Usuario eliminado.\n");
						} else {
							System.out.println("❌ No existe un usuario con ese ID.\n");
						}
						break;

					case "5":
						System.out.print("ID a actualizar: ");
						int idActualizar = Integer.parseInt(sc.nextLine());
						usuarioRepository.findById(idActualizar).ifPresentOrElse(usuario -> {
							System.out.print("Nuevo nombre [" + usuario.getNombre() + "]: ");
							String nuevoNombre = sc.nextLine();
							if (!nuevoNombre.isEmpty()) usuario.setNombre(nuevoNombre);

							System.out.print("Nueva edad [" + usuario.getEdad() + "]: ");
							String nuevaEdad = sc.nextLine();
							if (!nuevaEdad.isEmpty()) usuario.setEdad(Integer.parseInt(nuevaEdad));

							System.out.print("Nueva ciudad [" + usuario.getCiudad() + "]: ");
							String nuevaCiudad = sc.nextLine();
							if (!nuevaCiudad.isEmpty()) usuario.setCiudad(nuevaCiudad);

							usuarioRepository.save(usuario);
							System.out.println("✅ Usuario actualizado.\n");
						}, () -> System.out.println("❌ No existe un usuario con ese ID.\n"));
						break;

					case "6":
						ejecutar = false;
						System.out.println("Saliendo del ERP. ¡Hasta luego!");
						break;

					default:
						System.out.println("❌ Opción inválida.\n");
				}
			}

			sc.close();
		};
	}
}

