package utils;

import model.PersonaNatural;
import model.Usuario;
import model.Empresa; // Asegúrate de importar Empresa

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {

    public static List<Usuario> cargarUsuariosDesdeCSV(String rutaCsv) {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCsv))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar encabezado
                }

                String[] campos = linea.split(",");

                if (campos.length < 7) continue; // Evitar errores por líneas mal formateadas

                String nombre = campos[0].trim();
                String apellido = campos[1].trim();
                int edad = Integer.parseInt(campos[2].trim());
                String documento = campos[3].trim();
                String pais = campos[4].trim();
                String ciudad = campos[5].trim();
                String idioma = campos[6].trim();

                Usuario usuario;

                if (apellido == null || apellido.isEmpty()) {
                    usuario = new Empresa("Empresa", nombre, edad, documento, pais, ciudad, idioma); // ✅ Corregido
                } else {
                    usuario = new PersonaNatural(nombre, apellido, edad, pais, ciudad, documento, idioma);
                }

                usuarios.add(usuario);
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println(" Error al leer el archivo CSV: " + e.getMessage());
        } catch (Exception e) {
            System.err.println(" Registro inválido: " + e.getMessage());
        }

        return usuarios;
    }
}