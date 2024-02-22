package br.com.aguiar.dualwrites.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import br.com.aguiar.dualwrites.model.Pedido;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
    String SKIP_LOCKED = "-2";
    @QueryHints({ @QueryHint(name = "jakarta.persistence.lock.timeout", value = SKIP_LOCKED) })
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public List<Pedido> pedidosAbertos();


    @Query(value = "SELECT pg_notify(:topic, :json)", nativeQuery = true)
	void getPgNotifyPedido(@Param("topic") String topic, @Param("json") String json);

}
