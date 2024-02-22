package br.com.aguiar.dualwrites.payload.response;

import java.math.BigDecimal;

import br.com.aguiar.dualwrites.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoReponse {

    private Long id;

    private String nome;

    private BigDecimal quantidadade;

    public static ProdutoReponse topMap(Produto produto) {
        return new ProdutoReponse(produto.getId(), produto.getNome(), produto.getQuantidade());
    }

}
