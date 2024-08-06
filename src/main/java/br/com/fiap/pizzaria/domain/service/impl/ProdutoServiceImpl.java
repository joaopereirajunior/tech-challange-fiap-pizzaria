package br.com.fiap.pizzaria.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.ProdutoRepository;
import br.com.fiap.pizzaria.domain.service.ProdutoService;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoDTO;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    private final ProdutoRepository produtoRepository;
    
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


	@Override
	public List<Produto> buscarTodos() {
        List<Produto> produtoList = produtoRepository.findAll();
        return produtoList;
	}

	@Override
	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	@Override
	public Produto criar(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto atualizar(Long id, Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarPorId(Long id) {
		// TODO Auto-generated method stub
		
	}
}
