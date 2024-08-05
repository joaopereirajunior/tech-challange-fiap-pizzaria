package br.com.fiap.pizzaria.interfaceadapters;

import br.com.fiap.pizzaria.domain.model.Pedido;
import br.com.fiap.pizzaria.domain.service.PedidoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido cadastrarPedido(@RequestBody PedidoRequest pedidoRequest){

        return pedidoService.cadastrarPedido(pedidoRequest);

    }

}
