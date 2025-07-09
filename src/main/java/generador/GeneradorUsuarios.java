package generador;

import com.github.javafaker.Faker;
import model.PersonaNatural;
import model.Usuario;
import output.CsvExporter;
import utils.CsvLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

public class GeneradorUsuarios {

    private static final String ARCHIVO_CSV = "target/usuarios_latam.csv";
    private static final String[] PAISES_LATAM = {
            "Colombia", "Argentina", "Perú", "Chile", "Ecuador",
            "México", "Uruguay", "Bolivia", "Paraguay", "Venezuela"
    };

    private static final String[] CIUDADES_COLOMBIA = {
            "Bogotá", "Medellín", "Cali", "Barranquilla", "Bucaramanga"
    };

    public static List<Usuario> generarUsuarios(int cantidad) {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();
        List<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            String pais = PAISES_LATAM[random.nextInt(PAISES_LATAM.length)];
            String ciudad = pais.equals("Colombia") ?
                    CIUDADES_COLOMBIA[random.nextInt(CIUDADES_COLOMBIA.length)] :
                    faker.address().cityName();

            String nombre = faker.name().firstName();
            String apellido = faker.name().lastName();
            int edad = 10 + random.nextInt(71); // entre 10 y 80 años

            String documento = edad < 18
                    ? "11" + faker.number().digits(7)
                    : faker.number().digits(9);

            String idioma = "Español";

            try {
                PersonaNatural persona = new PersonaNatural(nombre, apellido, edad, pais, ciudad, documento, idioma);
                usuarios.add(persona);
            } catch (IllegalArgumentException e) {
                // Saltar usuarios no válidos (combinación repetida o restricciones de negocio)
                i--; // Intentar de nuevo
            }
        }

        // Eliminar archivo anterior
        CsvExporter.eliminarCsvSiExiste(ARCHIVO_CSV);

        // Exportar
        CsvExporter.exportar(usuarios, ARCHIVO_CSV);

        return usuarios;
    }

    public static PersonaNatural obtenerPersonaNaturalAleatoria(String rutaCsv) {
        List<PersonaNatural> personas = cargarPersonasDesdeCSV(rutaCsv);
        if (personas.isEmpty()) {
            throw new IllegalStateException("El archivo CSV no contiene personas naturales válidas.");
        }
        Random random = new Random();
        return personas.get(random.nextInt(personas.size()));
    }

    public static List<PersonaNatural> cargarPersonasDesdeCSV(String rutaCsv) {
        List<Usuario> usuarios = CsvLoader.cargarUsuariosDesdeCSV(rutaCsv);

        return usuarios.stream()
                .filter(u -> u instanceof PersonaNatural)
                .map(u -> (PersonaNatural) u)
                .collect(Collectors.toList());
    }
}
