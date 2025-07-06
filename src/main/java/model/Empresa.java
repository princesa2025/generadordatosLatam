package model;

/**
 * Clase Empresa
 * Aplica HERENCIA desde Usuario.
 * Aplica POLIMORFISMO sobrescribiendo getTipo().
 * Valida que el documento comience por '9' (regla de negocio para empresas).
 */
public class Empresa extends Usuario {

    // Constructor
    public Empresa(String nombre, String documento, String ciudad, String pais, String idioma) {
        super(nombre, documento, ciudad, pais, idioma);

        if (!documento.startsWith("9")) {
            throw new IllegalArgumentException("El documento de la empresa debe iniciar con '9'");
        }
    }

    // Sobrescribe el método abstracto de Usuario
    @Override
    public String getTipo() {
        return "Empresa";
    }
}
