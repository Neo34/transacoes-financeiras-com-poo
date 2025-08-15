
package br.com.banco.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Investimento(UUID id, UUID contaId, String produto, BigDecimal valor, Instant criadoEm) {
    public Investimento(UUID contaId, String produto, BigDecimal valor) {
        this(UUID.randomUUID(), contaId, produto, valor, Instant.now());
    }
}
