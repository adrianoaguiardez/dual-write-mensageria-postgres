package br.com.aguiar.dualwrites.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aguiar.dualwrites.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{


}
