package model;

/**
 * Clase PersonaNatural
 * Aplica HERENCIA desde Usuario.
 * Aplica POLIMORFISMO sobrescribiendo el método getTipo().
 */
public class PersonaNatural extends Usuario {

    private int edad;

    // Constructor
    public PersonaNatural(String nombre, String apellido, int edad, String documento,
                          String ciudad, String pais, String idioma) {

        // Concatenamos nombre completo
        super(nombre + " " + apellido, documento, ciudad, pais, idioma);

        if (edad < 10 || edad >= 80) {
            throw new IllegalArgumentException("Edad inválida: debe estar entre 10 y 80 años");
        }

        this.edad = edad;
    }

    // Getter
    public int getEdad() {
        return edad;
    }

    // Sobrescribimos método abstracto de Usuario
    @Override
    public String getTipo() {
        return "Persona Natural";
    }
}