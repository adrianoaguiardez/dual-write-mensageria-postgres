package br.com.aguiar.dualwrites.payload.response;

import br.com.aguiar.dualwrites.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteReponse {

    private Long id;

    private String nome;

    public static ClienteReponse topMap(Cliente cliente) {
        return new ClienteReponse(cliente.getId(), cliente.getNome());
    }

}
