package br.com.elisa.spring_boot_essentials.controller;

import br.com.elisa.spring_boot_essentials.DTO.ProdutoDTO;
import br.com.elisa.spring_boot_essentials.database.model.ProductEntity;
import br.com.elisa.spring_boot_essentials.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getProdutos(){
        return produtoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduto(@RequestBody ProdutoDTO produtoDTO){
        return produtoService.createProduct(produtoDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity updateProduct(@PathVariable Integer id,
                                       @RequestBody ProdutoDTO produtoDTO){
        return produtoService.updateProduct(produtoDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id){
        produtoService.deleteProduct(id);
    }
}
