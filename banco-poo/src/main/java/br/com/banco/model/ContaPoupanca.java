
package br.com.banco.model;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {
    private double taxaRendimentoMensal = 0.01; // 1% exemplo

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void aplicarRendimentoMensal() {
        BigDecimal rendimento = saldo.multiply(java.math.BigDecimal.valueOf(taxaRendimentoMensal));
        if (rendimento.compareTo(java.math.BigDecimal.ZERO) > 0) {
            saldo = saldo.add(rendimento);
            historico.add(new Transacao(TipoTransacao.RENDIMENTO, rendimento, this.id));
        }
    }

    @Override
    public String tipoConta() { return "POUPANCA"; }
}
