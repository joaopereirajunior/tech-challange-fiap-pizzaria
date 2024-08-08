package br.com.fiap.pizzaria.interfaceadapters.dto;

public record ClienteDTO(
		Long idCliente,
		String nome,
		String telefone,
		String endereco
) {}
