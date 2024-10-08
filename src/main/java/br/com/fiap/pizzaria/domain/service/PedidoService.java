package br.com.fiap.pizzaria.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoResponseDTO;

public interface PedidoService {
	
    /**
     * Retorna todos os registros do banco de dados.
     *
     * @return uma lista de todos os registros.
     */
	public List<PedidoResponseDTO> buscarTodos();
	
    /**
     * Retorna um registro específico pelo seu ID.
     *
     * @param id o ID do registro a ser buscado.
     * @return um Optional contendo o registro encontrado, ou vazio se não encontrado.
     */
    public Optional<PedidoResponseDTO> buscarPorId(Long id);
	
    /**
     * Cria um novo registro no banco de dados.
     *
     * @param pedidoRequestDTO a entidade a ser criada.
     * @return a entidade criada.
     */
    public PedidoResponseDTO cadastrarPedido(PedidoRequestDTO pedidoRequestDTO);
    
    /**
     * Atualiza um registro existente no banco de dados.
     *
     * @param pedido a entidade a ser criada.
     * @return a entidade criada.
     */
    public PedidoResponseDTO cancelarPedido(Long id);

}
