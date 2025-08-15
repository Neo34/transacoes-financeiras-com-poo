
package br.com.banco.model;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
    private BigDecimal limiteChequeEspecial = BigDecimal.ZERO;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    public ContaCorrente(Cliente cliente, BigDecimal limiteChequeEspecial) {
        super(cliente);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public synchronized boolean sacar(java.math.BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        BigDecimal disponivel = saldo.add(limiteChequeEspecial);
        if (disponivel.compareTo(valor) < 0) return false;
        saldo = saldo.subtract(valor);
        historico.add(new Transacao(TipoTransacao.SAQUE, valor, this.id));
        return true;
    }

    @Override
    public String tipoConta() { return "CORRENTE"; }
}
