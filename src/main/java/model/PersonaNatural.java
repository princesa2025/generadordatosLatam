package model;

import com.opencsv.bean.CsvBindByName;

import java.util.HashSet;
import java.util.Set;

public class PersonaNatural extends Usuario {

    private static final Set<String> combinacionesNombreApellido = new HashSet<>();
    private static final Set<String> documentosRegistrados = new HashSet<>();

    @CsvBindByName(column = "Apellido")
    private String apellido;

    public PersonaNatural() {
        // Constructor sin argumentos requerido por OpenCSV
        super();
    }

    public PersonaNatural(String nombre, String apellido, int edad, String pais, String ciudad, String documento, String idioma) {
        super("PersonaNatural", nombre, edad, documento, pais, ciudad, idioma);
        this.apellido = apellido;

        validarNombreApellidoUnicos(nombre, apellido);
        validarEdad(edad);
        validarDocumento(documento, edad);
        validarIdioma(pais, idioma);
    }

    private void validarNombreApellidoUnicos(String nombre, String apellido) {
        String combinacion = (nombre + "|" + apellido).toLowerCase();
        if (!combinacionesNombreApellido.add(combinacion)) {
            throw new IllegalArgumentException("La combinación de nombre y apellido ya fue registrada.");
        }
    }

    private void validarEdad(int edad) {
        if (edad < 10 || edad > 80) {
            throw new IllegalArgumentException("La edad debe estar entre 10 y 80 años.");
        }
    }

    private void validarDocumento(String documento, int edad) {
        if (!documento.matches("\\d+")) {
            throw new IllegalArgumentException("El documento debe ser numérico.");
        }

        if (edad < 18 && !documento.startsWith("11")) {
            throw new IllegalArgumentException("El documento de un menor debe comenzar por '11'.");
        }

        if (edad >= 18 && (documento.length() < 9 || documento.length() > 11)) {
            throw new IllegalArgumentException("El documento de un adulto debe tener entre 9 y 11 dígitos.");
        }

        if (!documentosRegistrados.add(documento)) {
            throw new IllegalArgumentException("El documento ya ha sido registrado previamente.");
        }
    }

    private void validarIdioma(String pais, String idioma) {
        if (!pais.equalsIgnoreCase("Colombia") && idioma.equalsIgnoreCase("Español")) {
            throw new IllegalArgumentException("Solo se permite idioma Español si el país es Colombia.");
        }
    }

    // GETTERS Y SETTERS REQUERIDOS POR OpenCSV
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
