package model;

/**
 * Clase abstracta Usuario
 * Aplica ABSTRACCIÓN: solo define lo común entre tipos de usuarios (PersonaNatural y Empresa).
 */
public abstract class Usuario {

    // Atributos encapsulados
    private String nombre;
    private String documento;
    private String ciudad;
    private String pais;
    private String idioma;

    // Constructor
    public Usuario(String nombre, String documento, String ciudad, String pais, String idioma) {
        this.nombre = nombre;
        this.documento = documento;
        this.ciudad = ciudad;
        this.pais = pais;
        this.idioma = idioma;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getIdioma() {
        return idioma;
    }

    // Método polimórfico que será sobrescrito en las subclases
    public abstract String getTipo();
}