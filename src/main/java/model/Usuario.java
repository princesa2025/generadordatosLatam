package model;

public abstract class Usuario {
    protected String tipo;
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected String documento;
    protected String pais;
    protected String ciudad;
    protected String idioma;

    // Constructor requerido por OpenCSV (usado por clases hijas como PersonaNatural)
    protected Usuario() {
        this.apellido = "";
    }

    public Usuario(String tipo, String nombre, int edad, String documento, String pais, String ciudad, String idioma) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.edad = edad;
        this.documento = documento;
        this.pais = pais;
        this.ciudad = ciudad;
        this.idioma = idioma;
        this.apellido = "";
    }

    // Getters
    public String getTipo() { return tipo; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad() { return edad; }
    public String getDocumento() { return documento; }
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public String getIdioma() { return idioma; }

    // Setters necesarios para OpenCSV
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setPais(String pais) { this.pais = pais; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
}
