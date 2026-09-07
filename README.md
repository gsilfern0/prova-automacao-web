# Automação WEB - Petz

Projeto de automação WEB desenvolvido para validar o valor de um produto entre a página do produto e a sacola no site da Petz.

## Tecnologias utilizadas

- Java 11+
- Maven
- Selenium WebDriver
- Cucumber
- JUnit 5

## Cenário automatizado

### CT01 - Validar valor do produto na sacola

O teste pesquisa e seleciona o produto informado no cenário, captura o preço exibido na página, adiciona o produto à sacola e compara os dois valores.

Como o teste utiliza um site real e o preço pode sofrer alterações, o valor é capturado durante a execução em vez de ser fixado no código.

## Como executar

Pré-requisitos:

- Java 11 ou superior
- Maven
- Google Chrome

Na raiz do projeto, execute:

```bash
mvn clean test
```

## Relatório e evidências

Após a execução, o relatório do Cucumber é gerado em:

```text
target/cucumber-report.html
```

Os screenshots dos principais passos ficam em:

```text
target/evidencias/
```

As evidências também são anexadas ao relatório do Cucumber. Em caso de falha, uma captura adicional é gerada automaticamente.

## Estrutura

```text
src/test/java
├── hooks
├── pages
├── runners
└── steps

src/test/resources
└── features
```
