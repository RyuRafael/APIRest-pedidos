package com.rafaelrr.d.baozistore.service;

import java.util.List;

import com.rafaelrr.d.baozistore.model.Produto;

public interface ProdutoService {
	
	public void insert(Produto produto);
	public void update(Produto produto);
	public Produto findId(Long id);
	public List<Produto> findAll();
	public void delete(Long id);

}
