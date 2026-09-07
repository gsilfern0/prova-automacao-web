Feature: Validar preco do produto no site Petz

  @CT01
  Scenario: CT01 - Validar valor do produto na sacola
    Given que o usuario acessa o site da Petz
    When seleciona o produto "Escada Baw & Miaw Grafite para Cães e Gatos"
    And adiciona o produto na sacola
    And acessa a sacola
    Then o valor do produto na sacola deve ser igual ao valor exibido na pagina do produto

  @CT02
  Scenario: CT02 - Validar total do produto ao aumentar a quantidade
    Given que o usuario acessa o site da Petz
    When seleciona o produto "Escada Baw & Miaw Grafite para Cães e Gatos"
    And adiciona o produto na sacola
    And acessa a sacola
    And aumenta a quantidade do produto em uma unidade
    Then o valor total do produto deve corresponder ao preco unitario multiplicado pela quantidade
