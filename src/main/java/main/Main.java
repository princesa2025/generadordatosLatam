package main;

import db.UsuarioDAO;
import generador.GeneradorUsuarios;
import model.PersonaNatural;
import model.Usuario;
import output.CsvExporter;
import utils.CsvLoader;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioDAO dao = new UsuarioDAO();

        System.out.println("=== SISTEMA DE USUARIOS LATAM ===");
        System.out.println("1. Generar usuarios nuevos");
        System.out.println("2. Cargar usuarios desde archivo CSV");
        System.out.print("Seleccione una opción (1 o 2): ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        List<Usuario> usuarios;

        if (opcion == 1) {
            System.out.print("¿Cuántos usuarios deseas generar? ");
            int cantidad = scanner.nextInt();

            // 1. Generar usuarios
            usuarios = GeneradorUsuarios.generarUsuarios(cantidad);

            // 2. Guardar en base de datos
            for (Usuario u : usuarios) {
                dao.guardarUsuario(u);
            }

            // 3. Exportar a CSV
            CsvExporter.exportar(usuarios, "target/usuariosLatam/usuarios_latam.csv");

            System.out.println("Generación completa. Se guardaron " + cantidad + " usuarios en la base de datos y en 'usuarios_latam.csv'.");

        } else if (opcion == 2) {
            // 1. Leer desde CSV (filtrando solo PersonaNatural)
            List<PersonaNatural> personas = CsvLoader.cargarUsuariosDesdeCSV("target/usuarios_latam.csv")
                    .stream()
                    .filter(u -> u instanceof PersonaNatural)
                    .map(u -> (PersonaNatural) u)
                    .collect(Collectors.toList());

            if (personas.isEmpty()) {
                System.out.println("No se encontraron usuarios válidos en el archivo.");
            } else {
                // 2. Guardar en base de datos
                for (Usuario u : personas) {
                    dao.guardarUsuario(u);
                }
                System.out.println("Usuarios cargados desde CSV y guardados en la base de datos: " + personas.size());
            }

        } else {
            System.out.println("Opción no válida. Finalizando programa.");
        }

        // Cerrar conexión a BD
        dao.cerrarConexion();
    }
}
