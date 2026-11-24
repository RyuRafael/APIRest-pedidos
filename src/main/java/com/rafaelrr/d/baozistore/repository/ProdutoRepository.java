package com.rafaelrr.d.baozistore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rafaelrr.d.baozistore.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	// comando query permite eu realizar consultas no banco de dados
	@Query("SELECT COUNT(p) > 0 FROM Produto p WHERE LOWER(p.nome) = LOWER(:nome)")
	boolean jaExisteEsseProduto(String nome);

}
