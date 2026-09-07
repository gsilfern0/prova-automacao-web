package steps;

import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.SacolaPage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class QuantidadeProdutoSteps {

    private SacolaPage sacolaPage;

    @When("aumenta a quantidade do produto em uma unidade")
    public void aumentaAQuantidadeDoProdutoEmUmaUnidade() {
        sacolaPage = new SacolaPage(Hooks.getDriver());

        sacolaPage.aumentarQuantidade();

        Hooks.salvarEvidencia("06_quantidade_aumentada");
    }

    @Then("o valor total do produto deve corresponder ao preco unitario multiplicado pela quantidade")
    public void validarValorTotalDoProduto() {
        String precoUnitarioTexto = sacolaPage.obterPrecoUnitarioProduto();
        int quantidade = sacolaPage.obterQuantidadeProduto();
        String totalProdutoTexto = sacolaPage.obterTotalProduto();

        BigDecimal precoUnitario = converterPreco(precoUnitarioTexto);
        BigDecimal totalObtido = converterPreco(totalProdutoTexto);

        BigDecimal totalEsperado = precoUnitario
            .multiply(BigDecimal.valueOf(quantidade))
            .setScale(2, RoundingMode.HALF_UP);

        System.out.println("Preço unitário: " + precoUnitarioTexto);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Total esperado: R$ " + formatarPreco(totalEsperado));
        System.out.println("Total exibido: " + totalProdutoTexto);

        Hooks.salvarEvidencia("07_total_produto");

        Assertions.assertEquals(
            0,
            totalEsperado.compareTo(totalObtido),
            "O valor total do produto não corresponde ao preço unitário multiplicado pela quantidade."
        );
    }

    private BigDecimal converterPreco(String preco) {
        String valorNormalizado = preco
            .replace("R$", "")
            .replace(".", "")
            .replace(",", ".")
            .trim();

        return new BigDecimal(valorNormalizado);
    }

    private String formatarPreco(BigDecimal valor) {
        return valor
            .setScale(2, RoundingMode.HALF_UP)
            .toString()
            .replace(".", ",");
    }
}
