package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * CLASE: DatabaseManager
 *
 * Se encarga de gestionar la conexión con la base de datos SQLite.
 * Aplica el principio de responsabilidad única (SRP del SOLID).
 */
public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:usuarios.db";

    // Retorna una conexión a la base de datos SQLite
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Crea la tabla "usuarios" si no existe
    public static void inicializarBaseDeDatos() {
        String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    documento TEXT NOT NULL UNIQUE,
                    ciudad TEXT,
                    pais TEXT,
                    idioma TEXT,
                    tipo TEXT
                );
                """;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}