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
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteResponseDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoResponseDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(description = "Retorna todos os pedidos.")
	@Override
	public List<PedidoResponseDTO> buscarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
        		.map(p -> converterPedidoEmPedidoResponseDTO(p))
                .collect(Collectors.toList());
	}

    @Operation(description = "Retorna o pedido pelo ID informado.")
	@Override
	public Optional<PedidoResponseDTO> buscarPorId(Long idPedido) {
		
	    Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

	    if (pedidoOpt.isPresent()) {
	    	Pedido pedido = pedidoOpt.get();
	    	PedidoResponseDTO pedidoDTO = converterPedidoEmPedidoResponseDTO(pedido);
	        return Optional.of(pedidoDTO);
	    } else {
	        return Optional.empty();
	    }
	}

    @Operation(description = "Efetua o cadastro de um novo pedido.")
	@Override
	public PedidoResponseDTO cadastrarPedido(PedidoRequestDTO pedidoDTO) {
	    Cliente cliente = clienteRepository.findById(pedidoDTO.Idcliente())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

	    Produto produto = produtoRepository.findById(pedidoDTO.IdProduto())
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	    Pedido pedido = new Pedido(produto, cliente, cliente.getEndereco(), StatusPedido.INICIO.getDescricao(), LocalDateTime.now());

	    Pedido pedidoSalvo = pedidoRepository.save(pedido);

	    return converterPedidoEmPedidoResponseDTO(pedidoSalvo);
	}

    @Operation(description = "Efetua o cancelamento de um produto existente pelo ID informado.")
	@Override
	public PedidoResponseDTO cancelarPedido(Long id) {
	    Pedido pedido = pedidoRepository.findById(id)
	        .orElseThrow(() -> new NoSuchElementException("Pedido com ID " + id + " não encontrado"));

	    pedido.setStatusPedido(StatusPedido.CANCELADO.getDescricao());
	    Pedido pedidoSalvo = pedidoRepository.save(pedido);
	    
	    return converterPedidoEmPedidoResponseDTO(pedidoSalvo);
	}
	
    public PedidoResponseDTO converterPedidoEmPedidoResponseDTO(Pedido pedido) {
        return new PedidoResponseDTO(
        		pedido.getIdPedido(),
        		pedido.getEnderecoEntrega(),
        		pedido.getStatusPedido(),
        		pedido.getDataPedido(),
        		converterClienteEmClienteDTO(pedido.getCliente()),
        		converterProdutoEmProdutoDTO(pedido.getProduto()));
    }
    
    public ClienteResponseDTO converterClienteEmClienteDTO(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getIdCliente(), cliente.getNome(), cliente.getTelefone(), cliente.getEndereco());
    }
    
    public ProdutoResponseDTO converterProdutoEmProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getTipo(), produto.getPreco());
    }
}