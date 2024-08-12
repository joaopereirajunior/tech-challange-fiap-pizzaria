package br.com.fiap.pizzaria.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.pizzaria.domain.model.ItensPedido;
import br.com.fiap.pizzaria.domain.model.Pedido;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {

	@Query("SELECT p FROM ItensPedido p WHERE p.pedido.id = :pedidoId")
	List<ItensPedido> findByPedidoId(Long pedidoId);

	@Query("SELECT p FROM ItensPedido p WHERE p.produto.id = :produtoId")
	List<ItensPedido> findByProdutoId(Long produtoId);
}