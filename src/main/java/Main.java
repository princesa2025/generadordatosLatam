import db.DatabaseManager;
import db.UsuarioDAO;
import generator.GeneradorUsuarios;
import model.Usuario;
import output.CsvExporter;

import java.util.List;
import java.util.Scanner;

/**
 * CLASE PRINCIPAL: Main
 *
 * Ejecuta el flujo completo: generación de datos, almacenamiento en base de datos (opcional) y exportación a CSV.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔢 ¿Cuántos usuarios deseas generar?");
        int cantidad = scanner.nextInt();

        // Paso 1: Inicializar la base de datos
        DatabaseManager.inicializarBaseDeDatos();

        // Paso 2: Generar usuarios
        GeneradorUsuarios generador = new GeneradorUsuarios();
        List<Usuario> usuarios = generador.generarUsuarios(cantidad);

        // Paso 3 (opcional): Guardar en base de datos
        UsuarioDAO dao = new UsuarioDAO();
        for (Usuario u : usuarios) {
            dao.guardar(u);
        }

        // Paso 4: Exportar a CSV
        CsvExporter.exportarUsuariosCSV(usuarios, "usuarios_generados.csv");
    }
}