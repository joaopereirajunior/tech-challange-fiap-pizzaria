package br.com.fiap.pizzaria.domain.model;

import java.util.Date;

import br.com.fiap.pizzaria.domain.enums.StatusPedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
	private String statusPedido;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_pedido")
	private Date dataPedido;
	
	public Pedido() {}

	public Pedido(Produto produto, Cliente cliente, String enderecoEntrega, String statusPedido, Date dataPedido) {
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

	public String getStatus() {
		return statusPedido;
	}

	public void setStatus(String status) {
		this.statusPedido = status;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
}
