package br.com.aguiar.dualwrites.controller.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MensagemError {

    private LocalDateTime dataTempo;

    private Integer status;

    private String erro;

    private String path;
    
}
