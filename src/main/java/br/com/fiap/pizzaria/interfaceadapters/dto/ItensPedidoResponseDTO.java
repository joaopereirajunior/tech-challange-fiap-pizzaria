package br.com.fiap.pizzaria.interfaceadapters.dto;

public record ItensPedidoResponseDTO(
		ProdutoResponseDTO produto,
		int quantidade
) {}
