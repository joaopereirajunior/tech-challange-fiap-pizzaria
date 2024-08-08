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

import br.com.fiap.pizzaria.domain.service.ClienteService;
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
   private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @Operation(description = "Retorna todos os clientes.")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos() {
        List<ClienteDTO> clientesDTO = clienteService.buscarTodos();
        return ResponseEntity.ok(clientesDTO);
    }

    @Operation(description = "Retorna o cliente pelo ID informado.")
    @GetMapping("/{idCliente}")
    public ResponseEntity<Optional<ClienteDTO>> recuperaClientePorId(@PathVariable Long idCliente){

        return ResponseEntity.ok(clienteService.buscarPorId(idCliente));
    }
    
    @Operation(description = "Efetua o cadastro de um novo cliente.")
    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCriado = clienteService.criar(clienteDTO);
        return ResponseEntity.status(201).body(clienteCriado);
    }
    
    @Operation(description = "Atualiza o cadastro de um cliente existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO clienteAtualizado = clienteService.atualizar(id, clienteDTO);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(description = "Deleta o cadastro de um cliente.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        clienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
