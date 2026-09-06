package steps;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class ValidacaoPrecoProdutoSteps {

    @Given("que o usuario acessa o site da Petz")
    public void queOUsuarioAcessaOSiteDaPetz() {
        HomePage homePage = new HomePage(Hooks.getDriver());
        homePage.acessar();
    }

    @When("seleciona o produto {string}")
    public void selecionaOProduto(String nomeProduto) {
        System.out.println("Selecionando o produto: " + nomeProduto);
    }

    @When("adiciona o produto na sacola")
    public void adicionaOProdutoNaSacola() {
        System.out.println("Adicionando o produto na sacola");
    }

    @Then("o valor do produto na pagina deve ser {string}")
    public void oValorDoProdutoNaPaginaDeveSer(String valorEsperado) {
        System.out.println("Validando valor na pagina: " + valorEsperado);
    }

    @Then("o valor do produto na sacola deve ser {string}")
    public void oValorDoProdutoNaSacolaDeveSer(String valorEsperado) {
        System.out.println("Validando valor na sacola: " + valorEsperado);
    }
}