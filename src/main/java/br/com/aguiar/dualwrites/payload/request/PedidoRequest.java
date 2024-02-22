package br.com.aguiar.dualwrites.payload.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.aguiar.dualwrites.model.Cliente;
import br.com.aguiar.dualwrites.model.ItemPedido;
import br.com.aguiar.dualwrites.model.Pedido;
import br.com.aguiar.dualwrites.model.Produto;
import br.com.aguiar.dualwrites.model.StatusPedido;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {

    @NotNull(message = "{pedido.cliente.not-null}")
    private ClienteRequest cliente;

    @NotNull(message = "{pedido.produtos.not-null}")
    @Size(min = 1, message = "pedido.produtos.size")
    private List<ItemPedidoRequest> produtos;

}
