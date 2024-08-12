package br.com.fiap.pizzaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.pizzaria.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
