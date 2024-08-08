package br.com.fiap.pizzaria.domain.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fiap.pizzaria.domain.enums.StatusPedido;
import br.com.fiap.pizzaria.domain.model.Cliente;
import br.com.fiap.pizzaria.domain.model.Pedido;
import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.ClienteRepository;
import br.com.fiap.pizzaria.domain.repository.PedidoRepository;
import br.com.fiap.pizzaria.domain.repository.ProdutoRepository;
import br.com.fiap.pizzaria.domain.service.PedidoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoDTO;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    

    public PedidoServiceImpl(ProdutoRepository produtoRepository, ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

	@Override
	public List<PedidoDTO> buscarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
        		.map(p -> converterPedidoEmPedidoDTO(p))
                .collect(Collectors.toList());
	}

	@Override
	public Optional<PedidoDTO> buscarPorId(Long idPedido) {
		
	    Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

	    if (pedidoOpt.isPresent()) {
	    	Pedido pedido = pedidoOpt.get();
	    	PedidoDTO pedidoDTO = converterPedidoEmPedidoDTO(pedido);
	        return Optional.of(pedidoDTO);
	    } else {
	        return Optional.empty();
	    }
	}

	@Override
	public PedidoDTO cadastrarPedido(PedidoDTO pedidoDTO) {
	    Cliente cliente = clienteRepository.findById(pedidoDTO.idCliente())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

	    Produto produto = produtoRepository.findById(pedidoDTO.idProduto())
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	    Pedido pedido = new Pedido(produto, cliente, cliente.getEndereco(), StatusPedido.INICIO.getDescricao(), LocalDateTime.now());

	    Pedido pedidoSalvo = pedidoRepository.save(pedido);

	    return converterPedidoEmPedidoDTO(pedidoSalvo);
	}

	@Override
	public PedidoDTO cancelarPedido(Long id) {
	    Pedido pedido = pedidoRepository.findById(id)
	        .orElseThrow(() -> new NoSuchElementException("Pedido com ID " + id + " não encontrado"));

	    pedido.setStatusPedido(StatusPedido.CANCELADO.getDescricao());
	    Pedido pedidoSalvo = pedidoRepository.save(pedido);
	    
	    return converterPedidoEmPedidoDTO(pedidoSalvo);
	}
	
    public PedidoDTO converterPedidoEmPedidoDTO(Pedido pedido) {
        return new PedidoDTO(pedido.getIdPedido(),
        		pedido.getCliente().getIdCliente(),
        		pedido.getProduto().getIdProduto(), 
        		pedido.getEnderecoEntrega(),
        		pedido.getStatusPedido());
    }
}