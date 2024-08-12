package br.com.fiap.pizzaria.interfaceadapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequestDTO(
		@NotNull(message = "Atributo enderecoEntrega é obrigatório e não pode estar em branco.")
		@NotBlank(message = "Atributo enderecoEntrega não pode estar em branco")
		String enderecoEntrega,
		@NotNull(message = "Atributo idCliente é obrigatório e não pode estar em branco.")
		Long idCliente,
		@NotNull(message = "Lista itensPedido é obrigatório e não pode estar em branco.")
		List<ItensPedidoRequestDTO> itensPedido

) {}
