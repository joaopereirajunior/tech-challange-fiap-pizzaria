package br.com.fiap.pizzaria.interfaceadapters.dto;

public record PedidoDTO(
		Long idCliente,
		Long idProduto,
		String endereco,
		String statusPedido
) {}
