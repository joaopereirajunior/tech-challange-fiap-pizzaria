package br.com.fiap.pizzaria.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;
	
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensPedido> itensPedido = new ArrayList<>();

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

	public Pedido(List<ItensPedido> itensPedido, Cliente cliente, String enderecoEntrega, String statusPedido, LocalDateTime dataPedido) {
		this.itensPedido = itensPedido;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
		this.statusPedido = statusPedido;
		this.dataPedido = dataPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public List<ItensPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
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
	
	public void addItem(ItensPedido item) {
	    itensPedido.add(item);
	    item.setPedido(this);
	}
}
