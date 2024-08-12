package br.com.fiap.pizzaria.interfaceadapters.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
		Long idPedido,
		String enderecoEntrega,
		String statusPedido,
		LocalDateTime dataPedido,
		ClienteResponseDTO cliente,
		List<ItensPedidoResponseDTO> itensPedido
) {}
