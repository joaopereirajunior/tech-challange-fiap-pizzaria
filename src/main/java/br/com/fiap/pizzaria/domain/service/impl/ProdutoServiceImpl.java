package br.com.fiap.pizzaria.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.pizzaria.domain.model.ItensPedido;
import br.com.fiap.pizzaria.domain.model.Pedido;
import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.ItensPedidoRepository;
import br.com.fiap.pizzaria.domain.repository.PedidoRepository;
import br.com.fiap.pizzaria.domain.repository.ProdutoRepository;
import br.com.fiap.pizzaria.domain.service.ProdutoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItensPedidoRepository itensPedidoRepository;
    
    public ProdutoServiceImpl(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository, ItensPedidoRepository itensPedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.itensPedidoRepository = itensPedidoRepository;
    }
	
    @Operation(description = "Retorna todos os produtos.")
	@Override
	public List<ProdutoResponseDTO> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        
        return produtos.stream()
                .map(p -> converterProdutoEmProdutoDTO(p))
                .collect(Collectors.toList());
	}

    @Operation(description = "Retorna o produto pelo ID informado.")
	@Override
	public Optional<ProdutoResponseDTO> buscarPorId(Long idProduto) {
		
	    Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

	    if (produtoOpt.isPresent()) {
	        Produto produto = produtoOpt.get();
	        ProdutoResponseDTO produtoDTO = converterProdutoEmProdutoDTO(produto);
	        return Optional.of(produtoDTO);
	    } else {
	        return Optional.empty();
	    }
	}

    @Operation(description = "Efetua o cadastro de um novo produto.")
	@Override
	public ProdutoResponseDTO criar(ProdutoRequestDTO produtoDTO) {
		
		Produto produto = new Produto(produtoDTO.nome(), produtoDTO.tipo(), produtoDTO.preco());
		Produto produtoSalvo = produtoRepository.save(produto);
		ProdutoResponseDTO produtoDTOResultado = converterProdutoEmProdutoDTO(produtoSalvo);
		
		return produtoDTOResultado;
	}

    @Operation(description = "Atualiza o cadastro de um produto existente.")
    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id).orElse(null);
        if (produtoExistente != null) {
        	
        	produtoExistente.setNome(produtoDTO.nome());
        	produtoExistente.setTipo(produtoDTO.tipo());
        	produtoExistente.setPreco(produtoDTO.preco());
        	
        	Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        	
        	return converterProdutoEmProdutoDTO(produtoAtualizado);
        }
        return null;
    }

	@Transactional
	@Override
	public void deletarPorId(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		// Verifica se o produto está associado a algum item de pedido
		List<ItensPedido> itensPedidos = itensPedidoRepository.findByProdutoId(id);

		if (!itensPedidos.isEmpty()) {
			// Exclui todos os itens pedidos associados ao produto
			itensPedidoRepository.deleteAll(itensPedidos);

			for (ItensPedido item : itensPedidos) {
				
				Pedido pedido = pedidoRepository.findById(item.getPedido().getIdPedido())
						.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

				pedidoRepository.delete(pedido);
			}
		}

		// Exclui o produto
		produtoRepository.delete(produto);
	}
    
    public ProdutoResponseDTO converterProdutoEmProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getTipo(), produto.getPreco());
    }

}
