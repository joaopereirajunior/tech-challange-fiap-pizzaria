package br.com.fiap.pizzaria.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.pizzaria.domain.enums.StatusPedido;
import br.com.fiap.pizzaria.domain.model.Cliente;
import br.com.fiap.pizzaria.domain.model.Pedido;
import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.PedidoRepository;
import br.com.fiap.pizzaria.domain.service.ClienteService;
import br.com.fiap.pizzaria.domain.service.PedidoService;
import br.com.fiap.pizzaria.domain.service.ProdutoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoDTO;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(ProdutoService produtoService, ClienteService clienteService, PedidoRepository pedidoRepository) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.pedidoRepository = pedidoRepository;
    }

	@Override
	public List<Pedido> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pedido> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Pedido cadastrarPedido(PedidoDTO pedidoDTO) {
		
        Cliente cliente = clienteService.buscarPorId(pedidoDTO.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        Produto produto = produtoService.buscarPorId(pedidoDTO.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Pedido pedidoForm = new Pedido(produto, cliente, cliente.getEndereco(), StatusPedido.INICIO.getDescricao(), LocalDateTime.now());

        return pedidoRepository.save(pedidoForm);
	}

	@Override
	public Pedido cancelarPedido(PedidoDTO pedidoDTO) {
		// TODO Auto-generated method stub
		return null;
	}


}
