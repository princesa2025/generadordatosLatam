package db;

import model.Usuario;

import java.sql.*;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./usuariosLatamDB", "sa", "");

            String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "tipo VARCHAR(50) NOT NULL," +
                    "nombre VARCHAR(100) NOT NULL," +
                    "edad INT NOT NULL," +
                    "documento VARCHAR(50) NOT NULL UNIQUE," +
                    "pais VARCHAR(50) NOT NULL," +
                    "ciudad VARCHAR(50) NOT NULL," +
                    "idioma VARCHAR(50) NOT NULL)";

            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver H2 no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void guardarUsuario(Usuario usuario) {
        if (conn == null) {
            System.out.println("No hay conexión a la base de datos.");
            return;
        }

        String sql = "INSERT INTO usuarios (tipo, nombre, edad, documento, pais, ciudad, idioma) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getTipo());
            ps.setString(2, usuario.getNombre());
            ps.setInt(3, usuario.getEdad());
            ps.setString(4, usuario.getDocumento());
            ps.setString(5, usuario.getPais());
            ps.setString(6, usuario.getCiudad());
            ps.setString(7, usuario.getIdioma());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
