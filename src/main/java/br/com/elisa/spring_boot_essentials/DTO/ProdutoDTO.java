package br.com.elisa.spring_boot_essentials.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidade;
}
