package br.com.fiap.pizzaria.interfaceadapters.dto;

import java.time.LocalDateTime;

public record PedidoResponseDTO(
		Long idPedido,
		String enderecoEntrega,
		String statusPedido,
		LocalDateTime dataPedido,
		ClienteResponseDTO cliente,
		ProdutoResponseDTO produto
) {}
