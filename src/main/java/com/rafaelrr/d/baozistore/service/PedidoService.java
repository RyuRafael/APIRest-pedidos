package com.rafaelrr.d.baozistore.service;

import java.util.List;

import com.rafaelrr.d.baozistore.model.Pedido;

public interface PedidoService {
	
	public void insert(Pedido pedido);
	public void update(Pedido pedido);
	public Pedido findId(Long id);
	public List<Pedido> findAll();
	public void delete(Long id);

}
