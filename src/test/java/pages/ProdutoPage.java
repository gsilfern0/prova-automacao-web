package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProdutoPage {

    private final WebDriverWait wait;

    private final By precoProduto = By.cssSelector(
            "#ecom-produto-price-default > p:first-of-type"
    );

    private final By botaoAdicionarSacola = By.cssSelector(
            "[data-testid='add-to-cart-button']"
    );

    private final By botaoIrParaSacola = By.cssSelector(
            "[data-testid='drawer-bag-checkout-button']"
    );

    public ProdutoPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String obterPreco() {
        WebElement preco = wait.until(
                ExpectedConditions.visibilityOfElementLocated(precoProduto)
        );

        return preco.getText().trim();
    }

    public void adicionarNaSacola() {
        WebElement botao = wait.until(
                ExpectedConditions.elementToBeClickable(botaoAdicionarSacola)
        );

        botao.click();
    }

    public void acessarSacola() {
        WebElement botao = wait.until(
                ExpectedConditions.elementToBeClickable(botaoIrParaSacola)
        );

        botao.click();
    }
}