package output;

import model.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * CLASE: CsvExporter
 *
 * Exporta una lista de usuarios a un archivo CSV.
 * Cumple con el requisito 6 de la prueba técnica.
 */
public class CsvExporter {

    // MÉTODO: Exporta la lista al archivo CSV
    public static void exportarUsuariosCSV(List<Usuario> usuarios, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            // Escribir encabezados
            writer.append("Nombre,Documento,Ciudad,Pais,Idioma,Tipo\n");

            // Escribir datos de cada usuario
            for (Usuario u : usuarios) {
                writer.append(u.getNombre()).append(",");
                writer.append(u.getDocumento()).append(",");
                writer.append(u.getCiudad()).append(",");
                writer.append(u.getPais()).append(",");
                writer.append(u.getIdioma()).append(",");
                writer.append(u.getTipo()).append("\n");
            }

            System.out.println("Archivo CSV generado exitosamente: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al generar el archivo CSV: " + e.getMessage());
        }
    }
}

