package br.com.fiap.pizzaria.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@Column(name = "endereco_entrega")
	private String enderecoEntrega;

	@Column(name = "status_pedido")
	private String statusPedido ;

	@Column(name = "data_pedido")
	private LocalDateTime dataPedido;

	public Pedido() {}

	public Pedido(Produto produto, Cliente cliente, String enderecoEntrega, String statusPedido, LocalDateTime dataPedido) {
		this.produto = produto;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
		this.statusPedido = statusPedido;
		this.dataPedido = dataPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String status) {
		this.statusPedido = status;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
}
