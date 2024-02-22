package br.com.aguiar.dualwrites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.aguiar.dualwrites.util.PostgresBean;

@EnableScheduling
@SpringBootApplication
public class DualWritesApplication {

	@Autowired
	private static ApplicationContext APPLICATION_CONTEXT;

	public static void main(String[] args) {

		APPLICATION_CONTEXT = SpringApplication.run(DualWritesApplication.class, args);
		PostgresBean postgresBean = DualWritesApplication.getBean(PostgresBean.class);

		postgresBean.run();
	}

	public static <T> T getBean(Class<T> requiredType) {

		return APPLICATION_CONTEXT.getBean(requiredType);
	}

	

}
