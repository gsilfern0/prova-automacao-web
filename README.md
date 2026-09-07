# Automação WEB - Petz

Projeto de automação WEB criado para validar o fluxo de um produto no site da Petz, desde a busca até a sacola.

A automação foi construída com Java, Selenium WebDriver e Cucumber.

## Cenários

### CT01 - Validar valor do produto na sacola

O cenário busca um produto, acessa sua página e captura o preço exibido. Depois, adiciona o produto à sacola e valida se o preço unitário continua o mesmo.

### CT02 - Validar total do produto ao aumentar a quantidade

O cenário aproveita o mesmo fluxo de busca e inclusão do produto na sacola. Na sacola, aumenta a quantidade para duas unidades e valida se o total apresentado corresponde ao preço unitário multiplicado pela quantidade.

Esse segundo cenário foi adicionado como complemento ao fluxo principal, principalmente para demonstrar a reutilização da estrutura criada e como ela pode ser aproveitada para novos testes.

Os preços não ficam fixos no código. Como o teste roda no site real da Petz, o valor do produto é capturado durante a própria execução.

## Tecnologias

- Java 11+
- Maven
- Selenium WebDriver
- Cucumber
- JUnit 5
- Google Chrome

## Executando o projeto

Com Java, Maven e Google Chrome instalados, abra o terminal na raiz do projeto e execute:

```bash
mvn clean test
```

Esse comando executa todos os cenários.

Também é possível executar cada cenário separadamente pelas tags:

**Somente CT01:**

```bash
mvn clean test "-Dcucumber.filter.tags=@CT01"
```

**Somente CT02:**

```bash
mvn clean test "-Dcucumber.filter.tags=@CT02"
```

## Relatório e evidências

Ao final da execução, o relatório do Cucumber fica disponível em:

```text
target/cucumber-report.html
```

Os screenshots são salvos dentro de `target/evidencias`, separados por cenário:

```text
target/evidencias/
├── CT01/
└── CT02/
```

As imagens também ficam anexadas aos respectivos passos no relatório do Cucumber. Caso um cenário falhe, uma evidência adicional da falha é gerada automaticamente.

## Estrutura do projeto

```text
src/test/java
├── hooks
├── pages
├── runners
└── steps

src/test/resources
└── features
```
