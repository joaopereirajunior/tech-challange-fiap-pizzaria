package br.com.fiap.pizzaria.interfaceadapters.dto;

public record PedidoDTO(
		Long idPedido,
		String endereco,
		String statusPedido,
		ClienteDTO cliente,
		ProdutoDTO produto
) {}
