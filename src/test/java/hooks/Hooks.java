package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hooks {

    private static WebDriver driver;
    private static Scenario scenarioAtual;

    @Before
    public void iniciarNavegador(Scenario scenario) {
        scenarioAtual = scenario;

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void finalizarCenario(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                salvarEvidencia("falha");
            }

            driver.quit();
            driver = null;
            scenarioAtual = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void salvarEvidencia(String nomeEvidencia) {
        if (driver == null) {
            return;
        }

        byte[] screenshot = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.BYTES);

        String identificador = obterIdentificadorCenario();

        if (scenarioAtual != null) {
            scenarioAtual.attach(
                screenshot,
                "image/png",
                identificador + "_" + nomeEvidencia
            );
        }

        try {
            Path pastaEvidencias = Paths.get(
                "target",
                "evidencias",
                identificador
            );

            Files.createDirectories(pastaEvidencias);

            String nomeArquivo = nomeEvidencia
                .replaceAll("[^a-zA-Z0-9-_]", "_");

            Path arquivo = pastaEvidencias.resolve(
                nomeArquivo + ".png"
            );

            Files.write(arquivo, screenshot);
        } catch (IOException e) {
            System.out.println(
                "Não foi possível salvar a evidência: " + e.getMessage()
            );
        }
    }

    private static String obterIdentificadorCenario() {
        if (scenarioAtual == null) {
            return "cenario";
        }

        Matcher matcher = Pattern.compile("CT\\d+")
            .matcher(scenarioAtual.getName());

        if (matcher.find()) {
            return matcher.group();
        }

        return scenarioAtual.getName()
            .replaceAll("[^a-zA-Z0-9-_]", "_");
    }
}
