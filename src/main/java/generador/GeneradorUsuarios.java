package generator;

import com.github.javafaker.Faker;
import model.Empresa;
import model.PersonaNatural;
import model.Usuario;

import java.util.*;

/**
 * CLASE: GeneradorUsuarios
 *
 * Genera usuarios (empresa o persona natural) válidos para LATAM.
 * Aplica reglas de negocio, validaciones, y evita duplicados.
 */
public class GeneradorUsuarios {

    private final Faker faker = new Faker(new Locale("es"));

    // Conjuntos para evitar duplicados
    private final Set<String> combinacionesNombre = new HashSet<>();
    private final Set<String> documentosGenerados = new HashSet<>();

    // Listado de países e idiomas asociados
    private final List<String> paises = Arrays.asList("Colombia", "Perú", "Ecuador", "Brasil", "Chile", "Argentina");
    private final Map<String, String> idiomas = Map.of(
            "Colombia", "Español",
            "Perú", "Quechua",
            "Ecuador", "Quichua",
            "Brasil", "Portugués",
            "Chile", "Español Chileno",
            "Argentina", "Español Rioplatense"
    );

    // MÉTODO PRINCIPAL: genera una lista de usuarios
    public List<Usuario> generarUsuarios(int cantidad) {
        List<Usuario> usuarios = new ArrayList<>();
        Random random = new Random();

        while (usuarios.size() < cantidad) {
            boolean esEmpresa = random.nextBoolean();
            String pais = paises.get(random.nextInt(paises.size()));
            String ciudad = faker.address().cityName();
            String idioma = idiomas.getOrDefault(pais, "Español");

            Usuario nuevo;

            if (esEmpresa) {
                nuevo = generarEmpresa(ciudad, pais, idioma);
            } else {
                nuevo = generarPersona(ciudad, pais, idioma);
            }

            if (nuevo != null) {
                usuarios.add(nuevo);
            }
        }

        return usuarios;
    }

    // FACTORY: Genera un usuario tipo persona natural
    private Usuario generarPersona(String ciudad, String pais, String idioma) {
        Random random = new Random();

        String nombre = faker.name().firstName();
        String apellido = faker.name().lastName();
        String combinacion = nombre + "-" + apellido;

        // Validar combinación única
        if (!combinacionesNombre.add(combinacion)) return null;

        int edad = 10 + random.nextInt(71); // entre 10 y 80

        String documento;
        do {
            if (edad < 18) {
                documento = "11" + (100000 + random.nextInt(900000)); // menor de edad
            } else {
                documento = String.valueOf(100000000 + random.nextInt(900000000)); // adulto
            }
        } while (!documentosGenerados.add(documento)); // evitar duplicado

        try {
            return new PersonaNatural(nombre, apellido, edad, documento, ciudad, pais, idioma);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // FACTORY: Genera un usuario tipo empresa
    private Usuario generarEmpresa(String ciudad, String pais, String idioma) {
        String nombreEmpresa = faker.company().name();

        // Validar nombre no duplicado
        if (!combinacionesNombre.add(nombreEmpresa)) return null;

        Random random = new Random();
        String documento;
        do {
            documento = "9" + (10000000 + random.nextInt(90000000)); // Documento de empresa
        } while (!documentosGenerados.add(documento)); // evitar duplicado

        try {
            return new Empresa(nombreEmpresa, documento, ciudad, pais, idioma);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}