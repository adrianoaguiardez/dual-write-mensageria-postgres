package br.com.aguiar.dualwrites.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.aguiar.dualwrites.model.Pedido;
import br.com.aguiar.dualwrites.payload.response.PedidoResponse;

public class ObjecMapperUtil {
    
    public static String toJson(Pedido pedido) {

        var mapper = new ObjectMapper();

        try {
            mapper.registerModule(new JavaTimeModule());
          
            return mapper.writeValueAsString(PedidoResponse.topMap(pedido));
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return null;
    }
}
