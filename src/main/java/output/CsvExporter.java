package output;

import model.Usuario;
import model.PersonaNatural;
import utils.CsvLoader;
import java.util.stream.Collectors;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CsvExporter {

    public static void exportar(List<? extends Usuario> listaUsuarios, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Escribir encabezados
            writer.append("Nombre,Apellido,Edad,Documento,Pais,Ciudad,Idioma\n");

            // Escribir datos
            for (Usuario usuario : listaUsuarios) {
                writer.append(usuario.getNombre()).append(",")
                        .append(usuario.getApellido()).append(",")
                        .append(String.valueOf(usuario.getEdad())).append(",")
                        .append(usuario.getDocumento()).append(",")
                        .append(usuario.getPais()).append(",")
                        .append(usuario.getCiudad()).append(",")
                        .append(usuario.getIdioma()).append("\n");
            }

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarCsvSiExiste(String nombreArchivo) {
        java.io.File archivo = new java.io.File(nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
    }
    public static PersonaNatural obtenerPersonaNaturalAleatoria(String rutaCsv) {
        List<Usuario> usuarios = CsvLoader.cargarUsuariosDesdeCSV(rutaCsv);

        // Filtrar solo PersonaNatural
        List<PersonaNatural> personasNaturales = usuarios.stream()
                .filter(u -> u instanceof PersonaNatural)
                .map(u -> (PersonaNatural) u)
                .collect(Collectors.toList());

        if (!personasNaturales.isEmpty()) {
            return personasNaturales.get(new Random().nextInt(personasNaturales.size()));
        }

        System.err.println("No se encontraron personas naturales en el archivo CSV.");
        return null;
    }
}
