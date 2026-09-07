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

        Hooks.salvarEvidencia("01_home");
    }

    @When("seleciona o produto {string}")
    public void selecionaOProduto(String nomeProduto) {
        homePage.buscarProduto(nomeProduto);

        Hooks.salvarEvidencia("02_resultado_busca");

        homePage.selecionarProduto(nomeProduto);

        produtoPage = new ProdutoPage(Hooks.getDriver());
        precoPaginaProduto = produtoPage.obterPreco();

        System.out.println("Preço capturado na página do produto: " + precoPaginaProduto);

        Hooks.salvarEvidencia("03_produto_preco");
    }

    @When("adiciona o produto na sacola")
    public void adicionaOProdutoNaSacola() {
        produtoPage.adicionarNaSacola();

        Hooks.salvarEvidencia("04_produto_adicionado");
    }

    @When("acessa a sacola")
    public void acessaASacola() {
        produtoPage.acessarSacola();

        sacolaPage = new SacolaPage(Hooks.getDriver());
        sacolaPage.aguardarCarregamento();

        Hooks.salvarEvidencia("05_sacola");
    }

    @Then("o valor do produto na sacola deve ser igual ao valor exibido na pagina do produto")
    public void validarValorDoProdutoNaSacola() {
        String precoSacola = sacolaPage.obterPrecoUnitarioProduto();

        System.out.println("Preço na página do produto: " + precoPaginaProduto);
        System.out.println("Preço unitário na sacola: " + precoSacola);

        Hooks.salvarEvidencia("06_sacola_preco");

        Assertions.assertEquals(
            precoPaginaProduto,
            precoSacola,
            "O preço do produto na sacola está diferente do preço exibido na página do produto."
        );
    }
}
