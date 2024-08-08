package br.com.fiap.pizzaria.interfaceadapters.dto;

public record ProdutoDTO(
		Long idProduto,
		String nome,
		String tipo,
		Double preco
) {}
