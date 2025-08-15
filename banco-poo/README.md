
# Banco POO — Projeto didático em Java (Maven)

Projeto para praticar conceitos de POO: herança, encapsulamento, polimorfismo, abstração e reuso de código.
Inclui persistência simples em JSON, Lombok, testes JUnit e workflow GitHub Actions.

## Como executar

Pré-requisitos: Java 17, Maven e Lombok (annotation processing ativado no IDE).

Compilar e executar no console:
```bash
mvn clean package
mvn exec:java -Dexec.mainClass="br.com.banco.Main"
```

Executar testes:
```bash
mvn test
```

## Estrutura
- `src/main/java/br/com/banco` — código-fonte
- `src/test/java/br/com/banco` — testes JUnit
- `data/contas.json` — arquivo gerado para persistência

Workflow GitHub Actions está configurado para rodar `mvn verify` em cada push/pr.
