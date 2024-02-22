package br.com.aguiar.dualwrites.payload.request;

import java.math.BigDecimal;

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
public class ProdutoRequest {

    private Long id;

    @NotBlank(message =  "{produto.nome.not-blank}")
    private String nome;

    @NotNull(message =  "{produto.quantidade.not-null}")
    private BigDecimal quantidade;

    @NotNull(message =  "{produto.preco.not-null}")
    private BigDecimal  preco;

    public Produto toMap() {
        return new Produto(null, nome, quantidade, preco);
    }

}
