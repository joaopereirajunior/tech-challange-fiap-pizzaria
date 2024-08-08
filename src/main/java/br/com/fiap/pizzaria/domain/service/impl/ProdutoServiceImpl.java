package br.com.fiap.pizzaria.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fiap.pizzaria.domain.model.Cliente;
import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.PedidoRepository;
import br.com.fiap.pizzaria.domain.repository.ProdutoRepository;
import br.com.fiap.pizzaria.domain.service.ProdutoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoDTO;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    
    public ProdutoServiceImpl(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }
	
	@Override
	public List<ProdutoDTO> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        
        return produtos.stream()
                .map(p -> converterProdutoEmProdutoDTO(p))
                .collect(Collectors.toList());
	}

	@Override
	public Optional<ProdutoDTO> buscarPorId(Long idProduto) {
		
	    Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

	    if (produtoOpt.isPresent()) {
	        Produto produto = produtoOpt.get();
	        ProdutoDTO produtoDTO = converterProdutoEmProdutoDTO(produto);
	        return Optional.of(produtoDTO);
	    } else {
	        return Optional.empty();
	    }
	}

	@Override
	public ProdutoDTO criar(ProdutoDTO produtoDTO) {
		
		Produto produto = new Produto(produtoDTO.nome(), produtoDTO.tipo(), produtoDTO.preco());
		Produto produtoSalvo = produtoRepository.save(produto);
		ProdutoDTO produtoDTOResultado = converterProdutoEmProdutoDTO(produtoSalvo);
		
		return produtoDTOResultado;
	}

    @Override
    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
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

    @Override
    public void deletarPorId(Long produtoId) {
    	// Deletando pedidos que contenham o produto informado
    	pedidoRepository.deleteByProdutoId(produtoId);
    	
    	// Deletando informações do cliente
    	produtoRepository.deleteById(produtoId);
    }
    
    public ProdutoDTO converterProdutoEmProdutoDTO(Produto produto) {
        return new ProdutoDTO(produto.getIdProduto(), produto.getNome(), produto.getTipo(), produto.getPreco());
    }
}
