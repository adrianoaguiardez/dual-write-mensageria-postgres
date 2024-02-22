package br.com.aguiar.dualwrites.payload.request;

import java.math.BigDecimal;

import br.com.aguiar.dualwrites.model.ItemPedido;
import br.com.aguiar.dualwrites.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoRequest {

    private Long id;

    private ProdutoRequest produto;

    @NotNull(message =  "{produto.quantidade.not-null}")
    private BigDecimal quantidade;

    public ItemPedido toMap() {
        return new ItemPedido(produto.toMap(), quantidade);
    }

}
