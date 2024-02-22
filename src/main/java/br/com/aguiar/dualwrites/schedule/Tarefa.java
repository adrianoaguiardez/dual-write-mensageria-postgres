package br.com.aguiar.dualwrites.schedule;


import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import br.com.aguiar.dualwrites.model.Pedido;
import br.com.aguiar.dualwrites.repository.PedidoRepository;
import br.com.aguiar.dualwrites.util.ObjecMapperUtil;

@Component
public class Tarefa {

    private final TransactionTemplate transactionTemplate;

    private final PedidoRepository pedidoRepository;

    public Tarefa(TransactionTemplate transactionTemplate, PedidoRepository pedidoRepository) {
        this.transactionTemplate = transactionTemplate;
        this.pedidoRepository = pedidoRepository;
    }

    @Scheduled(cron = "0 0/2 * * * *")
    public void verificarPedidoPendente() {
        boolean statusTransacional = true;
        while (statusTransacional) {

            statusTransacional = transactionTemplate.execute(transactionStatus -> {

                List<Pedido> pedidos = pedidoRepository.pedidosAbertos();

                if (pedidos.isEmpty()) {
                    return false;
                }

                boolean vericaPerguntaValidada = pedidos.stream().anyMatch(this::validarPedido);

                if (!vericaPerguntaValidada) {
                    return false;
                }

                return true;
            });
        }

    }

    private boolean validarPedido(Pedido pedido) {

        pedidoRepository.getPgNotifyPedido("list_pedido", ObjecMapperUtil.toJson(pedido));

        return false;
    }

}
