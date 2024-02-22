package br.com.aguiar.dualwrites.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.aguiar.dualwrites.exceptions.ObjectNotFoundException;
import br.com.aguiar.dualwrites.model.Pedido;
import br.com.aguiar.dualwrites.model.StatusPedido;
import br.com.aguiar.dualwrites.repository.PedidoRepository;

public class PostgresBean {

	@Autowired
	 private Environment environment;

	 @Autowired
	 private PedidoRepository pedidoRepository;

	

	public void run() {

		String urlBanco = environment.getProperty("spring.datasource.url");
		String userBanco = environment.getProperty("spring.datasource.username");
		String passwordBanco = environment.getProperty("spring.datasource.password");

		String url = urlBanco;
		Properties props = new Properties();
		props.setProperty("user", userBanco);

		props.setProperty("password", passwordBanco);

		try (Connection cn = DriverManager.getConnection(url, props)) {

			listenPostgres(cn);

			for (;;) {
				PGNotification[] ns = ((PGConnection) cn).getNotifications(0);

				if (ns != null) {
					listen(ns);
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void listenPostgres(Connection cn) throws SQLException {
		cn.createStatement().execute("LISTEN list_pedido");

	}

	private void listen(PGNotification[] ns) {
		for (PGNotification n : ns) {

			if (n.getName().equals("list_pedido")) {
				pedidoCarga(n);
			}

		}
	}

	private void pedidoCarga(PGNotification n) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.registerModule(new JavaTimeModule());
			Pedido pedido = mapper.readValue(n.getParameter(), Pedido.class);
			confirmarPagamento(pedido);

		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

	}

	private void confirmarPagamento(Pedido pedido) {
		Pedido pedidoExiste = pedidoRepository.findById(pedido.getId())
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
		pedidoExiste.setStatusPedido(StatusPedido.C);
		pedidoRepository.save(pedidoExiste);
	}

}
