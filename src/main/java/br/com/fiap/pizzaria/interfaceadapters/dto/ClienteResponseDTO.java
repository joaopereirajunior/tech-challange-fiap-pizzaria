package br.com.fiap.pizzaria.interfaceadapters.dto;

public record ClienteResponseDTO(
		Long idCliente,
		String nome,
		String telefone,
		String endereco
) {}
