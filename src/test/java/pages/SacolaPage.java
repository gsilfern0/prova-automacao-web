package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SacolaPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By precoUnitarioProduto = By.cssSelector(
        "[data-testid='ptz-bag-product-unit-price'] .money"
    );

    private final By quantidadeProduto = By.cssSelector(
        "[data-testid='ptz-bag-product-quantity']"
    );

    private final By botaoAumentarQuantidade = By.cssSelector(
        "[data-testid='ptz-bag-product-increment-quantity']"
    );

    private final By totalProduto = By.cssSelector(
        "[data-testid='ptz-bag-product-amount']"
    );

    public SacolaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void aguardarCarregamento() {
        wait.until(
            ExpectedConditions.presenceOfElementLocated(precoUnitarioProduto)
        );
    }

    public String obterPrecoUnitarioProduto() {
        WebElement preco = wait.until(
            ExpectedConditions.presenceOfElementLocated(precoUnitarioProduto)
        );

        return preco.getDomProperty("textContent").trim();
    }

    public int obterQuantidadeProduto() {
        WebElement quantidade = wait.until(
            ExpectedConditions.presenceOfElementLocated(quantidadeProduto)
        );

        return Integer.parseInt(
            quantidade.getDomProperty("value")
        );
    }

    public void aumentarQuantidade() {
        int quantidadeAntes = obterQuantidadeProduto();
        String totalAntes = obterTotalProduto();

        WebElement botao = wait.until(
            ExpectedConditions.visibilityOfElementLocated(botaoAumentarQuantidade)
        );

        Actions actions = new Actions(driver);

        actions.moveToElement(botao)
            .click()
            .perform();

        wait.until(
            driver -> obterQuantidadeProduto() == quantidadeAntes + 1
        );

        wait.until(
            driver -> !obterTotalProduto().equals(totalAntes)
        );
    }

    public String obterTotalProduto() {
        WebElement total = wait.until(
            ExpectedConditions.presenceOfElementLocated(totalProduto)
        );

        return total.getDomProperty("textContent").trim();
    }
}
