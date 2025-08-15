
package br.com.banco;

import br.com.banco.model.Cliente;
import br.com.banco.model.Conta;
import br.com.banco.service.BancoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class BancoServiceTest {
    private BancoService banco;

    @BeforeEach
    public void setup() {
        banco = new BancoService();
    }

    @Test
    public void testDepositoESaque() {
        Cliente c = banco.criarCliente("João", "11122233344");
        Conta conta = banco.criarContaCorrente(c);
        assertTrue(banco.depositar(c.getCpf(), BigDecimal.valueOf(100)));
        assertEquals(BigDecimal.valueOf(100), conta.getSaldo());
        assertTrue(banco.sacar(c.getCpf(), BigDecimal.valueOf(40)));
        assertEquals(BigDecimal.valueOf(60), conta.getSaldo());
    }

    @Test
    public void testTransferencia() {
        Cliente a = banco.criarCliente("Alice", "22233344455");
        Conta ca = banco.criarContaCorrente(a);
        Cliente b = banco.criarCliente("Bob", "33344455566");
        Conta cb = banco.criarContaCorrente(b);
        assertTrue(banco.depositar(a.getCpf(), BigDecimal.valueOf(200)));
        assertTrue(banco.transferir(a.getCpf(), b.getCpf(), BigDecimal.valueOf(120)));
        assertEquals(BigDecimal.valueOf(80), ca.getSaldo());
        assertEquals(BigDecimal.valueOf(120), cb.getSaldo());
    }

    @Test
    public void testInvestimento() {
        Cliente c = banco.criarCliente("Carol", "44455566677");
        Conta conta = banco.criarContaCorrente(c);
        assertTrue(banco.depositar(c.getCpf(), BigDecimal.valueOf(500)));
        assertTrue(banco.comprarInvestimento(c.getCpf(), "Tesouro", BigDecimal.valueOf(200)));
        assertEquals(BigDecimal.valueOf(300), conta.getSaldo());
    }
}
