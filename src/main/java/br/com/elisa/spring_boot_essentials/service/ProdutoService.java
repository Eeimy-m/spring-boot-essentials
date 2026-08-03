package br.com.elisa.spring_boot_essentials.service;

import br.com.elisa.spring_boot_essentials.DTO.ProdutoDTO;
import br.com.elisa.spring_boot_essentials.database.model.ProductEntity;
import br.com.elisa.spring_boot_essentials.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private static final List<ProductEntity> PRODUCTS = new ArrayList<>();

    static {
        PRODUCTS.add(ProductEntity.builder()
                .id(1)
                .nome("Notebook")
                .descricao("Galaxy Book 4, 64GB RAM, 458GB SSD")
                .preco(new BigDecimal(5000))
                .quantidade(50)
                .build()
        );
        PRODUCTS.add(ProductEntity.builder()
                .id(2)
                .nome("Iphone")
                .descricao("Iphone 17 Pro Max")
                .preco(new BigDecimal(15000))
                .quantidade(20)
                .build()
        );
        PRODUCTS.add(ProductEntity.builder()
                .id(3)
                .nome("Mouse")
                .descricao("Mouse")
                .preco(new BigDecimal(80))
                .quantidade(70)
                .build()
        );
    }

    public List<ProductEntity> findAll() {
        return new ArrayList<>(PRODUCTS);
    }

    public ProductEntity createProduct(ProdutoDTO produtoDTO) {
        int lastId = PRODUCTS.stream()
                .mapToInt(ProductEntity::getId)
                .max()
                .orElse(0) + 1;

        ProductEntity newProduct = ProductEntity.builder()
                .id(lastId)
                .nome(produtoDTO.getNome())
                .descricao(produtoDTO.getDescricao())
                .preco(produtoDTO.getPreco())
                .quantidade(produtoDTO.getQuantidade())
        .build();

        PRODUCTS.add(newProduct);
        return newProduct;
    }

    public ProductEntity updateProduct(ProdutoDTO produtoDTO, Integer id) {
        ProductEntity produto = PRODUCTS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());

        return produto;
    }

    public void deleteProduct(Integer id) {
        ProductEntity produto = PRODUCTS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->  new ProductNotFoundException("Product not found!"));

        PRODUCTS.remove(produto);
    }
}
