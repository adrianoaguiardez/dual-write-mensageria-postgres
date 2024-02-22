package br.com.aguiar.dualwrites.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aguiar.dualwrites.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{


}
