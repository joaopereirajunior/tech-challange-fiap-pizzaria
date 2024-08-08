package br.com.fiap.pizzaria.interfaceadapters.dto;

public record PedidoDTO(
		Long idPedido,
		Long idCliente,
		Long idProduto,
		String endereco,
		String statusPedido
) {}
