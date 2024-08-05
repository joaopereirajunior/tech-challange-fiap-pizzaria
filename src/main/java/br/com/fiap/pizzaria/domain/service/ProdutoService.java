package br.com.fiap.pizzaria.domain.service;

import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.ProdutoRepository;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    public ProdutoService (ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    public List<Produto> recuperarProdutos(){
        List<Produto> produtoList = produtoRepository.findAll();
        return produtoList;
    }

    public Produto salvarProduto (Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto converterProdutoRequestEmProduto(ProdutoRequest produtoRequest){

        Produto produto = new Produto();
        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());
        produto.setTipo(produtoRequest.getTipo());
        return produto;
    }

    public Optional<Produto> recuperarProdutoPorId(Long idProduto){
        return produtoRepository.findById(idProduto);
    }

}
