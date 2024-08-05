package br.com.fiap.pizzaria.domain.service;

import br.com.fiap.pizzaria.domain.enums.StatusPedido;
import br.com.fiap.pizzaria.domain.model.Cliente;
import br.com.fiap.pizzaria.domain.model.Pedido;
import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.PedidoRepository;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class PedidoService {

    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final PedidoRepository pedidoRepository;

    public PedidoService(ProdutoService produtoService, ClienteService clienteService, PedidoRepository pedidoRepository) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido cadastrarPedido(PedidoRequest pedidoRequest) {
        Cliente cliente = clienteService.recuperaClientePorId(pedidoRequest.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Produto produto = produtoService.recuperarProdutoPorId(pedidoRequest.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Pedido pedidoForm = new Pedido(produto, cliente, cliente.getEndereco(), StatusPedido.INICIO.getDescricao(), LocalDateTime.now());

        return pedidoRepository.save(pedidoForm);
    }

}
