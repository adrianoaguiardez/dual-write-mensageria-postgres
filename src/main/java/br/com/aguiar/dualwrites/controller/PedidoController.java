package br.com.aguiar.dualwrites.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.aguiar.dualwrites.exceptions.ObjectNotFoundException;
import br.com.aguiar.dualwrites.model.Cliente;
import br.com.aguiar.dualwrites.model.ItemPedido;
import br.com.aguiar.dualwrites.model.Pedido;
import br.com.aguiar.dualwrites.model.Produto;
import br.com.aguiar.dualwrites.payload.request.PedidoRequest;

import br.com.aguiar.dualwrites.payload.response.ProdutoReponse;
import br.com.aguiar.dualwrites.repository.ClienteRepository;
import br.com.aguiar.dualwrites.repository.ItemPedidoRepository;
import br.com.aguiar.dualwrites.repository.PedidoRepository;
import br.com.aguiar.dualwrites.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    final private ClienteRepository clienteRepository;

    final private ProdutoRepository produtoRepository;

    final private PedidoRepository pedidoRepository;

    final private ItemPedidoRepository itemPedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository,
            ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<ProdutoReponse> salvar(@RequestBody @Validated PedidoRequest pedidoRequest) {

        Pedido pedidoGravado = getPedidoGravado(pedidoRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedidoGravado.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    private Pedido getPedidoGravado(PedidoRequest pedidoRequest) {
        Pedido pedido = new Pedido();

        pedidoRequest.getProdutos().forEach(t -> {
            Produto produtoExiste = produtoRepository.findById(t.getProduto().getId())
                    .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

            pedido.adicionarItem(produtoExiste, t.getQuantidade());

        });
        Cliente clienteExiste = clienteRepository.findById(pedidoRequest.getCliente().getId())
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));

        pedido.setCliente(clienteExiste);
        Pedido pedidoGravado = pedidoRepository.save(pedido);

        pedidoGravado.getProdutos().forEach(p -> {
            p.setPedido(pedidoGravado);
            itemPedidoRepository.save(p);
        });
        return pedidoGravado;
    }

}
