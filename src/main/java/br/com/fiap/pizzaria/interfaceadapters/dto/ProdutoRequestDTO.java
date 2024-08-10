package br.com.fiap.pizzaria.interfaceadapters.dto;

public record ProdutoRequestDTO(
		String nome,
		String tipo,
		Double preco
) {}
