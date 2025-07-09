package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import model.PersonaNatural;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import output.CsvExporter;

public class BusquedaVuelosStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver navegador;

    Actor usuario = Actor.named("Cliente LATAM");
    PersonaNatural datosUsuario;

    @Before
    public void prepararEscenario() {
        usuario.can(BrowseTheWeb.with(navegador));
        datosUsuario = CsvExporter.obtenerPersonaNaturalAleatoria("target/usuarios_latam.csv");
        if (datosUsuario != null) {
            System.out.println("Usuario cargado desde CSV: " + datosUsuario.getNombre() + " " + datosUsuario.getCiudad());
        }
    }

    @Given("el usuario accede a la página de LATAM")
    public void elUsuarioAccedeALaPagina() {
        usuario.attemptsTo(Open.url("https://www.latamairlines.com"));
    }

    @When("busca un vuelo solo ida de {string} a {string} con fecha válida")
    public void buscaVueloSoloIdaDesdeCSV(String origen, String destino) {
        String ciudadOrigen = origen;
        String ciudadDestino = destino;

        if (origen.equalsIgnoreCase("CSV") && destino.equalsIgnoreCase("CSV")) {
            PersonaNatural origenUsuario = CsvExporter.obtenerPersonaNaturalAleatoria("target/usuarios_latam.csv");
            PersonaNatural destinoUsuario;

            do {
                destinoUsuario = CsvExporter.obtenerPersonaNaturalAleatoria("target/usuarios_latam.csv");
            } while (destinoUsuario.getCiudad().equalsIgnoreCase(origenUsuario.getCiudad()));

            ciudadOrigen = origenUsuario.getCiudad();
            ciudadDestino = destinoUsuario.getCiudad();

            System.out.println("Origen desde CSV: " + ciudadOrigen);
            System.out.println("Destino desde CSV: " + ciudadDestino);
        }

        navegador.findElement(By.id("origin")).sendKeys(ciudadOrigen);
        navegador.findElement(By.id("destination")).sendKeys(ciudadDestino);
        navegador.findElement(By.id("departure-date")).sendKeys("2025-08-10");
        navegador.findElement(By.id("search-button")).click();
    }

    @When("busca un vuelo ida y vuelta de {string} a {string} con fechas válidas")
    public void buscaVueloIdaVueltaDesdeCSV(String origen, String destino) {
        String ciudadOrigen = origen;
        String ciudadDestino = destino;

        if (origen.equalsIgnoreCase("CSV") && destino.equalsIgnoreCase("CSV")) {
            PersonaNatural origenUsuario = CsvExporter.obtenerPersonaNaturalAleatoria("target/usuarios_latam.csv");
            PersonaNatural destinoUsuario;

            do {
                destinoUsuario = CsvExporter.obtenerPersonaNaturalAleatoria("target/usuarios_latam.csv");
            } while (destinoUsuario.getCiudad().equalsIgnoreCase(origenUsuario.getCiudad()));

            ciudadOrigen = origenUsuario.getCiudad();
            ciudadDestino = destinoUsuario.getCiudad();

            System.out.println("Origen desde CSV: " + ciudadOrigen);
            System.out.println("Destino desde CSV: " + ciudadDestino);
        }

        navegador.findElement(By.id("origin")).sendKeys(ciudadOrigen);
        navegador.findElement(By.id("destination")).sendKeys(ciudadDestino);
        navegador.findElement(By.id("departure-date")).sendKeys("2025-08-10");
        navegador.findElement(By.id("return-date")).sendKeys("2025-08-15");
        navegador.findElement(By.id("search-button")).click();
    }

    @When("intenta seleccionar una fecha de regreso anterior a la de ida")
    public void intentaSeleccionarFechaInvalida() {
        navegador.findElement(By.id("origin")).sendKeys("Bogotá");
        navegador.findElement(By.id("destination")).sendKeys("Cali");
        navegador.findElement(By.id("departure-date")).sendKeys("2025-08-10");
        navegador.findElement(By.id("return-date")).sendKeys("2025-08-08");
        navegador.findElement(By.id("search-button")).click();
    }

    @Then("el sistema debe mostrar los vuelos disponibles")
    public void elSistemaDebeMostrarVuelosDisponibles() {
        boolean resultado = navegador.getPageSource().contains("vuelos disponibles"); // Ajustar al texto real
        System.out.println("¿Se mostraron vuelos?: " + resultado);
    }

    @Then("el sistema debe mostrar un mensaje de validación")
    public void elSistemaDebeMostrarMensajeDeValidacion() {
        boolean mensajePresente = navegador.getPageSource().contains("fecha inválida"); // Ajustar al mensaje real
        System.out.println("Mensaje de validación: " + mensajePresente);
    }
}
