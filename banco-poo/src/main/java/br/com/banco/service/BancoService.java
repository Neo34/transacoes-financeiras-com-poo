
package br.com.banco.service;

import br.com.banco.model.*;
import br.com.banco.repository.ContaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class BancoService {
    private final ContaRepository repo = ContaRepository.getInstance();

    public Cliente criarCliente(String nome, String cpf) {
        Cliente c = Cliente.builder().nome(nome).cpf(cpf).build();
        repo.salvarCliente(c);
        return c;
    }

    public Conta criarContaCorrente(Cliente cliente) {
        Conta c = new ContaCorrente(cliente);
        repo.salvarConta(c);
        return c;
    }

    public Conta criarContaPoupanca(Cliente cliente) {
        Conta c = new ContaPoupanca(cliente);
        repo.salvarConta(c);
        return c;
    }

    public boolean depositar(String cpf, BigDecimal valor) {
        Optional<Conta> opt = repo.buscarPorCpf(cpf);
        if (opt.isEmpty()) return false;
        opt.get().depositar(valor);
        repo.salvarConta(opt.get());
        return true;
    }

    public boolean sacar(String cpf, BigDecimal valor) {
        Optional<Conta> opt = repo.buscarPorCpf(cpf);
        if (opt.isEmpty()) return false;
        boolean ok = opt.get().sacar(valor);
        repo.salvarConta(opt.get());
        return ok;
    }

    public boolean transferir(String fromCpf, String toCpf, BigDecimal valor) {
        Optional<Conta> fromOpt = repo.buscarPorCpf(fromCpf);
        Optional<Conta> toOpt = repo.buscarPorCpf(toCpf);
        if (fromOpt.isEmpty() || toOpt.isEmpty()) return false;
        Conta from = fromOpt.get();
        Conta to = toOpt.get();
        synchronized (this) {
            boolean ok = from.sacar(valor);
            if (!ok) return false;
            to.depositar(valor);
            from.getHistorico().add(new Transacao(TipoTransacao.TRANSFERENCIA_ENVIADA, valor, from.getId()));
            to.getHistorico().add(new Transacao(TipoTransacao.TRANSFERENCIA_RECEBIDA, valor, to.getId()));
            repo.salvarConta(from);
            repo.salvarConta(to);
            return true;
        }
    }

    public boolean comprarInvestimento(String cpf, String produto, BigDecimal valor) {
        Optional<Conta> opt = repo.buscarPorCpf(cpf);
        if (opt.isEmpty()) return false;
        Conta c = opt.get();
        boolean ok = c.sacar(valor);
        if (!ok) return false;
        Investimento inv = new Investimento(c.getId(), produto, valor);
        repo.salvarInvestimento(inv);
        c.getHistorico().add(new Transacao(TipoTransacao.INVESTIMENTO_COMPRA, valor, c.getId()));
        repo.salvarConta(c);
        return true;
    }
}
