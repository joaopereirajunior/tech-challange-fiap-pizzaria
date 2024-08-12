package br.com.fiap.pizzaria.domain.enums;

public enum StatusPedido {
    INICIO("NOVO"),
    EM_PREPARACAO("EM PREPARAÇÃO"),
    FINALIZADO("FINALIZADO"),
    ENTREGUE("ENTREGUE"),
    CANCELADO("CANCELADO");
    
    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
