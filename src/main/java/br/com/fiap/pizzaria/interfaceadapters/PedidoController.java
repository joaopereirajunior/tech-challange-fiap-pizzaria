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
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoDTO;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> buscarTodos() {
        List<PedidoDTO> pedidos = pedidoService.buscarTodos();
        return ResponseEntity.ok(pedidos);
    }
    
    @GetMapping("/{idProduto}")
    public ResponseEntity<Optional<PedidoDTO>> buscarPorId(@PathVariable Long idPedido){
        return ResponseEntity.ok(pedidoService.buscarPorId(idPedido));
    }
    
    @PostMapping
    public PedidoDTO cadastrarPedido(@RequestBody PedidoDTO pedidoRequest){
        return pedidoService.cadastrarPedido(pedidoRequest);
    }
    
    @PatchMapping("/{idPedido}/cancelar")
    public PedidoDTO cancelarPedido(@PathVariable Long idPedido){
        return pedidoService.cancelarPedido(idPedido);
    }
}
