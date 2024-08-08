package br.com.fiap.pizzaria.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "preco")
	private Double preco;
	
	public Produto() {}

	public Produto(String nome, String tipo, Double preco) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.preco = preco;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
