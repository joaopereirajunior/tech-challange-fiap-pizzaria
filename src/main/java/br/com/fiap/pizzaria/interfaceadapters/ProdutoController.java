package br.com.fiap.pizzaria.interfaceadapters;

import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.ProdutoRepository;
import br.com.fiap.pizzaria.domain.service.ProdutoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodosProdutos(){

        return ResponseEntity.ok(produtoService.recuperarProdutos());
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody ProdutoRequest produtoRequest){
        return ResponseEntity.ok(produtoService.salvarProduto(produtoService.converterProdutoRequestEmProduto(produtoRequest)));

    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Optional<Produto>> recuperaProdutoPorId(@PathVariable Long idProduto){
        return ResponseEntity.ok(produtoService.recuperarProdutoPorId(idProduto));
    }

}
