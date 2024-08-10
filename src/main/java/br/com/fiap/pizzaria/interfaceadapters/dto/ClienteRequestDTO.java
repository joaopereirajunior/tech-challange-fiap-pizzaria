package br.com.fiap.pizzaria.interfaceadapters.dto;

public record ClienteRequestDTO(
		String nome,
		String telefone,
		String endereco
) {}
