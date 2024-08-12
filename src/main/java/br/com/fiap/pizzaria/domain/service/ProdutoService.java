package br.com.fiap.pizzaria.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoResponseDTO;

public interface ProdutoService {
	
    /**
     * Retorna todos os registros do banco de dados.
     *
     * @return uma lista de todos os registros.
     */
	public List<ProdutoResponseDTO> buscarTodos();
	
    /**
     * Retorna um registro específico pelo seu ID.
     *
     * @param id o ID do registro a ser buscado.
     * @return um Optional contendo o registro encontrado, ou vazio se não encontrado.
     */
    public Optional<ProdutoResponseDTO> buscarPorId(Long id);
	
    /**
     * Cria um novo registro no banco de dados.
     *
     * @param produtoRequestDTO a entidade a ser criada.
     * @return a entidade criada.
     */
    public ProdutoResponseDTO criar(ProdutoRequestDTO produtoRequestDTO);

    /**
     * Atualiza um registro existente no banco de dados.
     *
     * @param id o ID do registro a ser atualizado.
     * @param produtoRequestDTO a entidade com as novas informações.
     * @return a entidade atualizada.
     */
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoRequestDTO);

    /**
     * Deleta um registro do banco de dados pelo seu ID.
     *
     * @param id o ID do registro a ser deletado.
     */
    public void deletarPorId(Long id);

}
