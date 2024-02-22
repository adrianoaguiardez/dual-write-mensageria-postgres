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
import br.com.aguiar.dualwrites.model.Produto;
import br.com.aguiar.dualwrites.payload.request.ProdutoRequest;
import br.com.aguiar.dualwrites.payload.response.ProdutoReponse;
import br.com.aguiar.dualwrites.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    final private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoReponse> findById(@PathVariable Long id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return ResponseEntity.ok().body(
                ProdutoReponse.topMap(obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"))));
    }

    @PostMapping
    public ResponseEntity<ProdutoReponse> salvar(@RequestBody @Validated ProdutoRequest produtoRequest) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(produtoRepository.save(produtoRequest.toMap()).getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

}
