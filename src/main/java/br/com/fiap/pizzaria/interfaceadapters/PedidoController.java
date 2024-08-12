package br.com.fiap.pizzaria.interfaceadapters;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.pizzaria.domain.service.PedidoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedido", description = "Operações relacionadas a pedidos")
public class PedidoController {
	
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(description = "Retorna todos os pedidos.")
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> buscarTodos() {
        List<PedidoResponseDTO> pedidos = pedidoService.buscarTodos();
        return ResponseEntity.ok(pedidos);
    }
    
    @Operation(description = "Retorna o pedido pelo ID informado.")
    @GetMapping("/{idPedido}")
    public ResponseEntity<Optional<PedidoResponseDTO>> buscarPorId(@PathVariable Long idPedido){
        return ResponseEntity.ok(pedidoService.buscarPorId(idPedido));
    }
    
    @Operation(description = "Efetua o cadastro de um novo pedido.")
    @PostMapping
    public PedidoResponseDTO cadastrarPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO){
        return pedidoService.cadastrarPedido(pedidoRequestDTO);
    }
    
    @Operation(description = "Cancela o pedido pelo ID informado..")
    @PatchMapping("/{idPedido}/cancelar")
    public PedidoResponseDTO cancelarPedido(@PathVariable Long idPedido){
        return pedidoService.cancelarPedido(idPedido);
    }
}
