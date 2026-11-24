package com.rafaelrr.d.baozistore.service;

import com.rafaelrr.d.baozistore.model.Cliente;
import java.util.List;

public interface ClienteService {
	
	public void insert(Cliente cliente);
	public void update(Cliente cliente);
	public Cliente findId(Long id);
	public List<Cliente> findAll();
	public void delete(Long id);

}
