package br.com.fiap.pizzaria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.pizzaria.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
    @Modifying
    @Transactional
    @Query("DELETE FROM Pedido p WHERE p.cliente.id = :clienteId")
    void deleteByClienteId(Long clienteId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Pedido p WHERE p.produto.id = :produtoId")
    void deleteByProdutoId(Long produtoId);

}
