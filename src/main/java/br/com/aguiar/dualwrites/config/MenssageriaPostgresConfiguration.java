package br.com.aguiar.dualwrites.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aguiar.dualwrites.util.PostgresBean;

@Configuration
public class MenssageriaPostgresConfiguration {
    @Bean
	public PostgresBean cargaPostgresBean() {
		return new PostgresBean();
	}
}
