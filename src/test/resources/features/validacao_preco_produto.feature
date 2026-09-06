Feature: Validar preco do produto no site Petz

  Scenario: CT01 - Validar valor do produto na sacola
    Given que o usuario acessa o site da Petz
    When seleciona o produto "Escada Baw & Miaw Grafite para Cães e Gatos"
    And adiciona o produto na sacola
    Then o valor do produto na pagina deve ser "R$ 202,99"
    And o valor do produto na sacola deve ser "R$ 202,99"