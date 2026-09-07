package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SacolaPage {

    private final WebDriverWait wait;

    private final By precoUnitarioProduto = By.cssSelector(
            "[data-testid='ptz-bag-product-unit-price'] .money"
    );

    public SacolaPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String obterPrecoUnitarioProduto() {
        WebElement preco = wait.until(
                ExpectedConditions.presenceOfElementLocated(precoUnitarioProduto)
        );

        return preco.getDomProperty("textContent").trim();
    }
}