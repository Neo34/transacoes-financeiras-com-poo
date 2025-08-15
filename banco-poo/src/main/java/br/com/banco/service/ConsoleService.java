
package br.com.banco.service;

import br.com.banco.model.Cliente;
import br.com.banco.model.Conta;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleService {
    private final Scanner sc = new Scanner(System.in);
    private final BancoService banco = new BancoService();

    public void run() {
        while (true) {
            System.out.println("--- Banco POO ---");
            System.out.println("1. Criar cliente e conta corrente");
            System.out.println("2. Criar cliente e conta poupança");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir (PIX)");
            System.out.println("6. Comprar investimento");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            String op = sc.nextLine();
            try {
                switch (op) {
                    case "1" -> criarContaCorrente();
                    case "2" -> criarContaPoupanca();
                    case "3" -> depositar();
                    case "4" -> sacar();
                    case "5" -> transferir();
                    case "6" -> comprarInvestimento();
                    case "0" -> { System.out.println("Tchau"); return; }
                    default -> System.out.println("Opção inválida"); 
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void criarContaCorrente() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CPF: "); String cpf = sc.nextLine();
        Cliente c = banco.criarCliente(nome, cpf);
        Conta conta = banco.criarContaCorrente(c);
        System.out.println("Conta corrente criada: " + conta.getId());
    }

    private void criarContaPoupanca() {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("CPF: "); String cpf = sc.nextLine();
        Cliente c = banco.criarCliente(nome, cpf);
        Conta conta = banco.criarContaPoupanca(c);
        System.out.println("Conta poupança criada: " + conta.getId());
    }

    private void depositar() {
        System.out.print("CPF da conta: "); String cpf = sc.nextLine();
        System.out.print("Valor: "); java.math.BigDecimal v = new java.math.BigDecimal(sc.nextLine());
        boolean ok = banco.depositar(cpf, v);
        System.out.println(ok ? "Depósito realizado" : "Conta não encontrada");
    }

    private void sacar() {
        System.out.print("CPF da conta: "); String cpf = sc.nextLine();
        System.out.print("Valor: "); java.math.BigDecimal v = new java.math.BigDecimal(sc.nextLine());
        boolean ok = banco.sacar(cpf, v);
        System.out.println(ok ? "Saque realizado" : "Saldo insuficiente ou conta não encontrada");
    }

    private void transferir() {
        System.out.print("CPF origem: "); String from = sc.nextLine();
        System.out.print("CPF destino: "); String to = sc.nextLine();
        System.out.print("Valor: "); java.math.BigDecimal v = new java.math.BigDecimal(sc.nextLine());
        boolean ok = banco.transferir(from, to, v);
        System.out.println(ok ? "Transferência realizada" : "Falha na transferência");
    }

    private void comprarInvestimento() {
        System.out.print("CPF da conta: "); String cpf = sc.nextLine();
        System.out.print("Produto: "); String produto = sc.nextLine();
        System.out.print("Valor: "); java.math.BigDecimal v = new java.math.BigDecimal(sc.nextLine());
        boolean ok = banco.comprarInvestimento(cpf, produto, v);
        System.out.println(ok ? "Investimento comprado" : "Falha na compra");
    }
}
