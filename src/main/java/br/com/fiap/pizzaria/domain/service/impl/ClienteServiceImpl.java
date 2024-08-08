package br.com.fiap.pizzaria.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fiap.pizzaria.domain.model.Cliente;
import br.com.fiap.pizzaria.domain.model.Produto;
import br.com.fiap.pizzaria.domain.repository.ClienteRepository;
import br.com.fiap.pizzaria.domain.repository.PedidoRepository;
import br.com.fiap.pizzaria.domain.service.ClienteService;
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.PedidoDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ProdutoDTO;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

	@Override
	public List<ClienteDTO> buscarTodos() {
		
        List<Cliente> clientes = clienteRepository.findAll();
        
        return clientes.stream()
                .map(p -> converterClienteEmClienteDTO(p))
                .collect(Collectors.toList());
	}

	@Override
	public Optional<ClienteDTO> buscarPorId(Long idCliente) {
		
	    Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);

	    if (clienteOpt.isPresent()) {
	        Cliente cliente = clienteOpt.get();
	        ClienteDTO clienteDTO = converterClienteEmClienteDTO(cliente);
	        return Optional.of(clienteDTO);
	    } else {
	        return Optional.empty();
	    }
	}
	
	@Override
	public ClienteDTO criar(ClienteDTO clienteDTO) {
		
	    Cliente cliente = new Cliente(clienteDTO.nome(),clienteDTO.telefone(), clienteDTO.endereco());
	    Cliente clienteSalvo = clienteRepository.save(cliente);
	    ClienteDTO clienteDTOResultado = converterClienteEmClienteDTO(clienteSalvo);
	    
	    return clienteDTOResultado;
	}
    
	@Override
	public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
	    Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
	    if (clienteExistente != null) {

	        clienteExistente.setNome(clienteDTO.nome());
	        clienteExistente.setEndereco(clienteDTO.endereco());
	        clienteExistente.setTelefone(clienteDTO.telefone());
	        
	        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
	        
	        return converterClienteEmClienteDTO(clienteAtualizado);
	    }
	    return null;
	}

    @Override
    public void deletarPorId(Long clienteId) {
    	
    	pedidoRepository.deleteByClienteId(clienteId);
    	
        clienteRepository.deleteById(clienteId);
    }
    
    
    public ClienteDTO converterClienteEmClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getIdCliente(), cliente.getNome(), cliente.getTelefone(), cliente.getEndereco());
    }
}
