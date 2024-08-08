package br.com.fiap.pizzaria.domain.service;

import java.util.List;
import java.util.Optional;

import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteDTO;

public interface ClienteService {
	
    /**
     * Retorna todos os registros do banco de dados.
     *
     * @return uma lista de todos os registros.
     */
	public List<ClienteDTO> buscarTodos();
	
    /**
     * Retorna um registro específico pelo seu ID.
     *
     * @param id o ID do registro a ser buscado.
     * @return um Optional contendo o registro encontrado, ou vazio se não encontrado.
     */
    public Optional<ClienteDTO> buscarPorId(Long id);
	
    /**
     * Cria um novo registro no banco de dados.
     *
     * @param clienteDTO a entidade a ser criada.
     * @return a entidade criada.
     */
    public ClienteDTO criar(ClienteDTO clienteDTO);

    /**
     * Atualiza um registro existente no banco de dados.
     *
     * @param id o ID do registro a ser atualizado.
     * @param clienteDTO a entidade com as novas informações.
     * @return a entidade atualizada.
     */
    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO);

    /**
     * Deleta um registro do banco de dados pelo seu ID.
     *
     * @param id o ID do registro a ser deletado.
     */
    public void deletarPorId(Long id);
	

}
