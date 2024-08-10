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
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(description = "Retorna todos os produtos.")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarTodosProdutos(){

        return ResponseEntity.ok(produtoService.buscarTodos());
    }

    @Operation(description = "Efetua o cadastro de um novo produto.")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody ProdutoRequestDTO produtoDTO){
        return ResponseEntity.ok(produtoService.criar(produtoDTO));

    }

    @Operation(description = "Retorna o produto pelo ID informado.")
    @GetMapping("/{idProduto}")
    public ResponseEntity<Optional<ProdutoResponseDTO>> buscarPorId(@PathVariable Long idProduto){
        return ResponseEntity.ok(produtoService.buscarPorId(idProduto));
    }
    
    @Operation(description = "Atualiza o cadastro de um produto existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoDTO) {
        try {
            ProdutoResponseDTO produtoAtualizado = produtoService.atualizar(id, produtoDTO);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(description = "Deleta o cadastro de um produto pelo ID informado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
    	produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
