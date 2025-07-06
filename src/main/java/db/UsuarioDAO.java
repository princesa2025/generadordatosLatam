package db;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * CLASE: UsuarioDAO
 *
 * Permite guardar objetos Usuario (Empresa o PersonaNatural) en la base de datos.
 * Aplica el patrón DAO y el principio de responsabilidad única (SRP del SOLID).
 */
public class UsuarioDAO {

    // Inserta un usuario en la tabla
    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, documento, ciudad, pais, idioma, tipo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getDocumento());
            pstmt.setString(3, usuario.getCiudad());
            pstmt.setString(4, usuario.getPais());
            pstmt.setString(5, usuario.getIdioma());
            pstmt.setString(6, usuario.getTipo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
        }
    }
}