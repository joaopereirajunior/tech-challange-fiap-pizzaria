package br.com.fiap.pizzaria.interfaceadapters.dto;

import java.util.List;

public record PedidoRequestDTO(
		String enderecoEntrega,
		Long Idcliente,
		List<ItensPedidoRequestDTO> itensPedido

) {}
