package br.com.fiap.pizzaria.interfaceadapters;

import br.com.fiap.pizzaria.domain.model.Cliente;

import br.com.fiap.pizzaria.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
   private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Optional<Cliente>> recuperaClientePorId(@PathVariable Long idCliente){

        return ResponseEntity.ok(clienteService.recuperaClientePorId(idCliente));
    }
}
