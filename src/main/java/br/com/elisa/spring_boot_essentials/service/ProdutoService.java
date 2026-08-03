package br.com.elisa.spring_boot_essentials.service;

import br.com.elisa.spring_boot_essentials.DTO.ProdutoDTO;
import br.com.elisa.spring_boot_essentials.database.model.ProdutoEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private static final List<ProdutoEntity> PRODUTOS = new ArrayList<>();

    static {
        PRODUTOS.add(ProdutoEntity.builder()
                .id(1)
                .nome("Notebook")
                .descricao("Galaxy Book 4, 64GB RAM, 458GB SSD")
                .preco(new BigDecimal(5000))
                .quantidade(50)
                .build()
        );
        PRODUTOS.add(ProdutoEntity.builder()
                .id(2)
                .nome("Iphone")
                .descricao("Iphone 17 Pro Max")
                .preco(new BigDecimal(15000))
                .quantidade(20)
                .build()
        );
        PRODUTOS.add(ProdutoEntity.builder()
                .id(3)
                .nome("Mouse")
                .descricao("Mouse")
                .preco(new BigDecimal(80))
                .quantidade(70)
                .build()
        );
    }

    public List<ProdutoEntity> findAll() {
        return new ArrayList<>(PRODUTOS);
    }

    public ProdutoEntity createProduct(ProdutoDTO produtoDTO) {
        int lastId = PRODUTOS.stream()
                .mapToInt(ProdutoEntity::getId)
                .max()
                .orElse(0) + 1;

        ProdutoEntity newProduct = ProdutoEntity.builder()
                .id(lastId)
                .nome(produtoDTO.getNome())
                .descricao(produtoDTO.getDescricao())
                .preco(produtoDTO.getPreco())
                .quantidade(produtoDTO.getQuantidade())
        .build();

        PRODUTOS.add(newProduct);
        return newProduct;
    }

    public ProdutoEntity updateProduct(ProdutoDTO produtoDTO, Integer id) {
        ProdutoEntity produto = PRODUTOS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());

        return produto;
    }

    public void deleteProduct(Integer id) {
        ProdutoEntity produto = PRODUTOS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->  new RuntimeException("Product not found"));

        PRODUTOS.remove(produto);
    }
}
