package br.com.elisa.spring_boot_essentials.database.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoEntity {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidade;
}
