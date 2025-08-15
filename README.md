 🏦 Banco POO

Projeto de estudo em Java Orientado a Objetos (POO) simulando um sistema bancário.
Permite gerenciar clientes, contas (corrente, poupança e investimento) e realizar transações como depósitos, saques e transferências.

🚀 Tecnologias

Java 17+

Maven

JUnit 5 (testes)

JSON para persistência de dados

📂 Estrutura do Projeto
banco-poo/
├── pom.xml                     # Configuração do Maven
├── src/main/java/br/com/banco/
│   ├── Main.java               # Ponto de entrada da aplicação
│   ├── model/                  # Entidades do sistema
│   │   ├── Cliente.java
│   │   ├── Conta.java
│   │   ├── ContaCorrente.java
│   │   ├── ContaPoupanca.java
│   │   ├── Investimento.java
│   │   ├── Transacao.java
│   │   └── TipoTransacao.java
│   ├── repository/             # Persistência
│   │   └── ContaRepository.java
│   ├── service/                # Regras de negócio
│   │   ├── BancoService.java
│   │   └── ConsoleService.java
│   └── util/                   # Utilitários
│       └── JsonUtil.java
├── src/test/java/br/com/banco/ # Testes
│   └── BancoServiceTest.java
├── data/contas.json            # Dados persistidos
└── README.md

⚙️ Como rodar o projeto
Pré-requisitos

Java 17+ instalado

Maven instalado

Executando
# Clonar o repositório
git clone https://github.com/seu-usuario/banco-poo.git
cd banco-poo

# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn exec:java -Dexec.mainClass="br.com.banco.Main"

🧪 Testes

O projeto contém testes unitários com JUnit.
Para rodar os testes:

mvn test

📊 Funcionalidades

Criar clientes e contas

Depósitos, saques e transferências

Consulta de saldo

Registro de transações

Persistência em JSON

💡 Futuras melhorias

Interface gráfica ou API REST

Autenticação de clientes

Suporte a mais tipos de investimentos

Relatórios financeiros

👨‍💻 Autor(César)

Projeto desenvolvido para fins educacionais como prática de Java + POO + Maven.
