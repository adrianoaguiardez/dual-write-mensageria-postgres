package br.com.aguiar.dualwrites.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.aguiar.dualwrites.exceptions.ObjectNotFoundException;
import br.com.aguiar.dualwrites.model.Cliente;
import br.com.aguiar.dualwrites.payload.request.ClienteRequest;
import br.com.aguiar.dualwrites.payload.response.ClienteReponse;
import br.com.aguiar.dualwrites.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    final private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteReponse> findById(@PathVariable Long id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return ResponseEntity.ok().body(
                ClienteReponse.topMap(obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"))));
    }

    @PostMapping
    public ResponseEntity<ClienteReponse> salvar(@RequestBody @Validated ClienteRequest clienteRequest) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteRepository.save(clienteRequest.toMap()).getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

}
