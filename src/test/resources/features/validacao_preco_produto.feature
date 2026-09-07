Feature: Validar preco do produto no site Petz

  Scenario: CT01 - Validar valor do produto na sacola
    Given que o usuario acessa o site da Petz
    When seleciona o produto "Escada Baw & Miaw Grafite para Cães e Gatos"
    And adiciona o produto na sacola
    And acessa a sacola
    Then o valor do produto na sacola deve ser igual ao valor exibido na pagina do produto