package br.com.aguiar.dualwrites.payload.request;

import br.com.aguiar.dualwrites.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
   
    private Long id;


    @NotBlank(message =  "{cliente.nome.not-blank}")
    private String nome;

    public Cliente toMap(){
        return new Cliente(null, nome);
    }

}
