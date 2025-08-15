
package br.com.banco.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    private UUID id = UUID.randomUUID();
    private String nome;
    private String cpf;
}
