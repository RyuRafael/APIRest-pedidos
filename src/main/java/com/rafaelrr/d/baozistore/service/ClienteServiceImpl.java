package com.rafaelrr.d.baozistore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelrr.d.baozistore.model.Cliente;
import com.rafaelrr.d.baozistore.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;

	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	@Override
	public void insert(Cliente cliente) {

		clienteRepository.save(cliente);

	}

	@Transactional
	@Override
	public void update(Cliente cliente) {
		Cliente clien = clienteRepository.findById(cliente.getId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		clien.setNome(cliente.getNome());
		clienteRepository.save(clien);
	}

	@Override
	public Cliente findId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	@Override
	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

	@Override
	public void Delete(Long id) {

		Cliente buscarCliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		clienteRepository.delete(buscarCliente);

	}
}
