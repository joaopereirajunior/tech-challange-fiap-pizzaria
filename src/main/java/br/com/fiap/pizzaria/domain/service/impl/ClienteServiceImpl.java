package br.com.fiap.pizzaria.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.fiap.pizzaria.domain.model.Cliente;
import br.com.fiap.pizzaria.domain.repository.ClienteRepository;
import br.com.fiap.pizzaria.domain.repository.PedidoRepository;
import br.com.fiap.pizzaria.domain.service.ClienteService;
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteRequestDTO;
import br.com.fiap.pizzaria.interfaceadapters.dto.ClienteResponseDTO;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

	@Override
	public List<ClienteResponseDTO> buscarTodos() {
		
        List<Cliente> clientes = clienteRepository.findAll();
        
        return clientes.stream()
                .map(p -> converterClienteEmClienteDTO(p))
                .collect(Collectors.toList());
	}

	@Override
	public Optional<ClienteResponseDTO> buscarPorId(Long idCliente) {
		
	    Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);

	    if (clienteOpt.isPresent()) {
	        Cliente cliente = clienteOpt.get();
	        ClienteResponseDTO clienteDTO = converterClienteEmClienteDTO(cliente);
	        return Optional.of(clienteDTO);
	    } else {
	        return Optional.empty();
	    }
	}
	
	@Override
	public ClienteResponseDTO criar(ClienteRequestDTO clienteDTO) {
		
	    Cliente cliente = new Cliente(clienteDTO.nome(), clienteDTO.telefone(), clienteDTO.endereco());
	    Cliente clienteSalvo = clienteRepository.save(cliente);
	    ClienteResponseDTO clienteDTOResultado = converterClienteEmClienteDTO(clienteSalvo);
	    
	    return clienteDTOResultado;
	}
    
	@Override
	public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDTO) {
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
    
    
    public ClienteResponseDTO converterClienteEmClienteDTO(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getIdCliente(), cliente.getNome(), cliente.getTelefone(), cliente.getEndereco());
    }
}
