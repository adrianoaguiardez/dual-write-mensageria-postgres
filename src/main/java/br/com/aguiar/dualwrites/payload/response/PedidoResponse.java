package br.com.aguiar.dualwrites.payload.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.aguiar.dualwrites.model.Cliente;
import br.com.aguiar.dualwrites.model.ItemPedido;
import br.com.aguiar.dualwrites.model.Pedido;
import br.com.aguiar.dualwrites.model.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private Long id;

    private BigDecimal total;

    private StatusPedido statusPedido;

    private LocalDateTime dataPedido;

    private Cliente cliente;

   
    public static PedidoResponse topMap(Pedido pedido) {
        return new PedidoResponse(pedido.getId(), pedido.getTotal(), pedido.getStatusPedido(), pedido.getDataPedido(), pedido.getCliente());
    }

}
