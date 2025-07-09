package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/busquedavuelos.feature",
        glue = "stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@BusquedaVuelos"
)
public class BusquedaVuelosRunner {
}
