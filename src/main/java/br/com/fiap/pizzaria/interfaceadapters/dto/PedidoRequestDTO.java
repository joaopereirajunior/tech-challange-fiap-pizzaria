package br.com.fiap.pizzaria.interfaceadapters.dto;

public record PedidoRequestDTO(
		String enderecoEntrega,
		Long Idcliente,
		Long IdProduto

) {}
