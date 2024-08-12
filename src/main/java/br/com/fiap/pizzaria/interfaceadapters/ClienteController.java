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
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Operações relacionadas a clientes")
public class ClienteController {
	
   private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @Operation(description = "Retorna todos os clientes.")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> buscarTodos() {
        List<ClienteResponseDTO> clientesDTO = clienteService.buscarTodos();
        return ResponseEntity.ok(clientesDTO);
    }

    @Operation(description = "Retorna o cliente pelo ID informado.")
    @GetMapping("/{idCliente}")
    public ResponseEntity<Optional<ClienteResponseDTO>> recuperaClientePorId(@PathVariable Long idCliente){

        return ResponseEntity.ok(clienteService.buscarPorId(idCliente));
    }
    
    @Operation(description = "Efetua o cadastro de um novo cliente.")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO clienteCriado = clienteService.criar(clienteRequestDTO);
        return ResponseEntity.status(201).body(clienteCriado);
    }
    
    @Operation(description = "Atualiza o cadastro de um cliente existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteRequestDTO) {
        try {
            ClienteResponseDTO clienteAtualizado = clienteService.atualizar(id, clienteRequestDTO);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(description = "Deleta o cadastro de um cliente pelo ID informado.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        clienteService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
