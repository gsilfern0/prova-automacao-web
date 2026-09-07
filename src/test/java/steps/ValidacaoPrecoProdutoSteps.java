package steps;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import pages.ProdutoPage;
import pages.SacolaPage;

public class ValidacaoPrecoProdutoSteps {

    private HomePage homePage;
    private ProdutoPage produtoPage;
    private SacolaPage sacolaPage;
    private String precoPaginaProduto;

    @Given("que o usuario acessa o site da Petz")
    public void queOUsuarioAcessaOSiteDaPetz() {
        homePage = new HomePage(Hooks.getDriver());
        homePage.acessar();
    }

    @When("seleciona o produto {string}")
    public void selecionaOProduto(String nomeProduto) {
        homePage.buscarProduto(nomeProduto);
        homePage.selecionarProduto(nomeProduto);

        produtoPage = new ProdutoPage(Hooks.getDriver());
        precoPaginaProduto = produtoPage.obterPreco();

        System.out.println("Preco capturado na pagina do produto: " + precoPaginaProduto);
    }

    @When("adiciona o produto na sacola")
    public void adicionaOProdutoNaSacola() {
        produtoPage.adicionarNaSacola();
    }

    @When("acessa a sacola")
    public void acessaASacola() {
        produtoPage.acessarSacola();
        sacolaPage = new SacolaPage(Hooks.getDriver());
    }

    @Then("o valor do produto na sacola deve ser igual ao valor exibido na pagina do produto")
    public void validarValorDoProdutoNaSacola() {
        String precoSacola = sacolaPage.obterPrecoUnitarioProduto();

        System.out.println("Preco na pagina do produto: " + precoPaginaProduto);
        System.out.println("Preco unitario na sacola: " + precoSacola);

        Assertions.assertEquals(
                precoPaginaProduto,
                precoSacola,
                "O preco do produto na sacola esta diferente do preco exibido na pagina do produto."
        );
    }
}