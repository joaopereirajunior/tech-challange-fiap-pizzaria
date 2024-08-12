package br.com.fiap.pizzaria.interfaceadapters.dto;

public record ProdutoResponseDTO(
		Long idProduto,
		String nome,
		String tipo,
		Double preco
) {}
