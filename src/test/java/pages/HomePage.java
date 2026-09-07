package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String URL = "https://www.petz.com.br/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By campoBusca = By.cssSelector("[data-testid='headerSearch']");
    private final By botaoAceitarCookies = By.id("onetrust-accept-btn-handler");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void acessar() {
        driver.get(URL);
        wait.until(ExpectedConditions.urlContains("petz.com.br"));
        aceitarCookiesSeNecessario();
    }

    public void buscarProduto(String nomeProduto) {
        WebElement busca = wait.until(
                ExpectedConditions.elementToBeClickable(campoBusca)
        );

        busca.clear();
        busca.sendKeys(nomeProduto);
        busca.sendKeys(Keys.ENTER);
    }

    public void selecionarProduto(String nomeProduto) {
        By produto = By.xpath(
                "//p[contains(@class,'pcs__name') and normalize-space()=\""
                        + nomeProduto
                        + "\"]/ancestor::a[1]"
        );

        WebElement linkProduto = wait.until(
                ExpectedConditions.elementToBeClickable(produto)
        );

        linkProduto.click();
    }

    private void aceitarCookiesSeNecessario() {
        try {
            WebDriverWait waitCookies = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(5)
            );

            WebElement botao = waitCookies.until(
                    ExpectedConditions.elementToBeClickable(botaoAceitarCookies)
            );

            botao.click();
        } catch (TimeoutException | NoSuchElementException ignored) {
            //Adicionei pra evitar que o aviso de cookies impacte em alguma ação durante o teste.
        }
    }
}