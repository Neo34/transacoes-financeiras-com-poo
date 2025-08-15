
package br.com.banco.repository;

import br.com.banco.model.Conta;
import br.com.banco.model.Cliente;
import br.com.banco.model.Investimento;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ContaRepository {
    private static final ContaRepository INSTANCE = new ContaRepository();
    private final Map<UUID, Conta> contas = new ConcurrentHashMap<>();
    private final Map<UUID, Cliente> clientes = new ConcurrentHashMap<>();
    private final Map<UUID, Investimento> investimentos = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final File dataFile = new File("data/contas.json");

    private ContaRepository() {
        load();
    }

    public static ContaRepository getInstance() { return INSTANCE; }

    public void salvarCliente(Cliente c) { clientes.put(c.getId(), c); persist(); }
    public Optional<Cliente> buscarCliente(UUID id) { return Optional.ofNullable(clientes.get(id)); }

    public void salvarConta(Conta c) { contas.put(c.getId(), c); persist(); }
    public Optional<Conta> buscarConta(UUID id) { return Optional.ofNullable(contas.get(id)); }
    public Optional<Conta> buscarPorCpf(String cpf) {
        return contas.values().stream().filter(c -> c.getCliente().getCpf().equals(cpf)).findFirst();
    }
    public Collection<Conta> todasContas() { return List.copyOf(contas.values()); }

    public void salvarInvestimento(Investimento inv) { investimentos.put(inv.id(), inv); persist(); }
    public Collection<Investimento> todosInvestimentos() { return List.copyOf(investimentos.values()); }

    private synchronized void persist() {
        try {
            Map<String, Object> dump = new HashMap<>();
            dump.put("contas", contas);
            dump.put("clientes", clientes);
            dump.put("investimentos", investimentos);
            mapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, dump);
        } catch (Exception e) {
            // falha na persistência é não-crítica para o exercício
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private synchronized void load() {
        try {
            if (!dataFile.exists()) return;
            Map<String, Object> dump = mapper.readValue(dataFile, new TypeReference<Map<String, Object>>(){});
            // carregamento parcial — para manter o exemplo simples, não reconstituímos objetos completos
            // em produção usar DTOs/TypeReference com classes específicas.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
