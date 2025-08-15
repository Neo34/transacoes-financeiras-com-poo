
package br.com.banco.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Transacao(TipoTransacao tipo, BigDecimal valor, UUID contaId, Instant ocorrencia) {
    public Transacao(TipoTransacao tipo, BigDecimal valor, java.util.UUID contaId) {
        this(tipo, valor, contaId, Instant.now());
    }
}
