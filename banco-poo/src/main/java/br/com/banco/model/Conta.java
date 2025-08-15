
package br.com.banco.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Conta {
    protected final UUID id;
    protected final Cliente cliente;
    protected BigDecimal saldo;
    protected final List<Transacao> historico = new ArrayList<>();

    protected Conta(Cliente cliente) {
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.saldo = BigDecimal.ZERO;
    }

    public UUID getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public BigDecimal getSaldo() { return saldo; }
    public List<Transacao> getHistorico() { return List.copyOf(historico); }

    public synchronized void depositar(java.math.BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        saldo = saldo.add(valor);
        historico.add(new Transacao(TipoTransacao.DEPOSITO, valor, this.id));
    }

    public synchronized boolean sacar(java.math.BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        if (saldo.compareTo(valor) < 0) return false;
        saldo = saldo.subtract(valor);
        historico.add(new Transacao(TipoTransacao.SAQUE, valor, this.id));
        return true;
    }

    public abstract String tipoConta();
}
