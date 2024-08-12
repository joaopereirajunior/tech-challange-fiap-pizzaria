package br.com.fiap.pizzaria.interfaceadapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDTO(
		@NotNull(message = "Atributo nome é obrigatório e não pode estar em branco.")
		@NotBlank(message = "Atributo nome não pode estar em branco")
		String nome,
		@NotNull(message = "Atributo telefone é obrigatório e não pode estar em branco.")
		@NotBlank(message = "Atributo telefone não pode estar em branco")
		String telefone,
		@NotNull(message = "Atributo endereco é obrigatório e não pode estar em branco.")
		@NotBlank(message = "Atributo endereco não pode estar em branco")
		String endereco
) {}
