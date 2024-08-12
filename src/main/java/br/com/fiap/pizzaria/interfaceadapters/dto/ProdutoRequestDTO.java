package br.com.fiap.pizzaria.interfaceadapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO(
		@NotNull(message = "Atributo nome é obrigatório e não pode estar em branco.")
		@NotBlank(message = "Atributo nome não pode estar em branco")
		String nome,
		@NotNull(message = "Atributo tipo é obrigatório e não pode estar em branco.")
		@NotBlank(message = "Atributo tipo não pode estar em branco")
		String tipo,
		@NotNull(message = "Atributo preco é obrigatório e não pode estar em branco.")
		Double preco
) {}
