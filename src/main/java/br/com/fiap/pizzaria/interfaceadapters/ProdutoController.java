package br.com.fiap.pizzaria.interfaceadapters;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<ProdutoDTO>> buscarTodosProdutos(){

        return ResponseEntity.ok(produtoService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        return ResponseEntity.ok(produtoService.criar(produtoDTO));

    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Optional<ProdutoDTO>> buscarPorId(@PathVariable Long idProduto){
        return ResponseEntity.ok(produtoService.buscarPorId(idProduto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        try {
            ProdutoDTO produtoAtualizado = produtoService.atualizar(id, produtoDTO);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
    	produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
