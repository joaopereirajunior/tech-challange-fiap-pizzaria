package br.com.fiap.pizzaria.interfaceadapters;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.service.ProdutoService;
import br.com.fiap.pizzaria.domain.service.impl.ProdutoServiceImpl;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoDTO;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodosProdutos(){

        return ResponseEntity.ok(produtoService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody ProdutoDTO produtoRequest){
        return ResponseEntity.ok(produtoService.criar(converterProdutoDTOEmProduto(produtoRequest)));

    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Optional<Produto>> buscarPorId(@PathVariable Long idProduto){
        return ResponseEntity.ok(produtoService.buscarPorId(idProduto));
    }
    
    public Produto converterProdutoDTOEmProduto(ProdutoDTO produtoRequest){
        Produto produto = new Produto();
        produto.setNome(produtoRequest.nome());
        produto.setPreco(produtoRequest.preco());
        produto.setTipo(produtoRequest.tipo());
        return produto;
    }

}
