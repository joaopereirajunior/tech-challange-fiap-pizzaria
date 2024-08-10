package br.com.fiap.pizzaria.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fiap.pizzaria.domain.model.Produto;
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
    
    public ProdutoServiceImpl(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
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

    @Operation(description = "Deleta o cadastro de um produto.")
    @Override
    public void deletarPorId(Long produtoId) {
    	// Deletando pedidos que contenham o produto informado
    	pedidoRepository.deleteByProdutoId(produtoId);
    	
    	// Deletando informações do cliente
    	produtoRepository.deleteById(produtoId);
    }
    
    public ProdutoResponseDTO converterProdutoEmProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getTipo(), produto.getPreco());
    }
}
