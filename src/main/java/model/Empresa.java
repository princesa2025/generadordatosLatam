package model;

import java.util.HashSet;
import java.util.Set;

public class Empresa extends Usuario {

    private static final Set<String> combinacionesNombreApellido = new HashSet<>();
    private static final Set<String> documentosRegistrados = new HashSet<>();

    public Empresa(String nombre, String apellido, int edad, String documento, String pais, String ciudad, String idioma) {
        super("Empresa", nombre, edad, documento, pais, ciudad, idioma);

        validarEdad(edad);
        validarApellido(apellido);
        validarNombreApellidoUnicos(nombre, apellido);
        validarDocumento(documento);
        validarIdioma(pais, idioma);
    }

    private void validarEdad(int edad) {
        if (edad < 10 || edad > 80) {
            throw new IllegalArgumentException("La edad debe estar entre 10 y 80 años.");
        }
    }

    private void validarApellido(String apellido) {
        if (apellido != null && !apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'apellido' debe estar en blanco para empresas.");
        }
    }

    private void validarNombreApellidoUnicos(String nombre, String apellido) {
        String combinacion = (nombre + "|" + apellido).toLowerCase();
        if (!combinacionesNombreApellido.add(combinacion)) {
            throw new IllegalArgumentException("La combinación de nombre y apellido ya fue registrada.");
        }
    }

    private void validarDocumento(String documento) {
        if (!documento.matches("\\d+")) {
            throw new IllegalArgumentException("El documento debe ser numérico.");
        }

        if (!documento.startsWith("9")) {
            throw new IllegalArgumentException("El documento de una empresa debe comenzar con '9'.");
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
}
