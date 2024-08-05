package br.com.fiap.pizzaria.interfaceadapters.dto;

import br.com.fiap.pizzaria.domain.model.Produto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ProdutoRequest {


    private String nome;

    private String tipo;

    private Double preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


}
