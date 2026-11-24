package com.rafaelrr.d.baozistore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelrr.d.baozistore.model.Cliente;
import com.rafaelrr.d.baozistore.model.Pedido;
import com.rafaelrr.d.baozistore.repository.ClienteRepository;
import com.rafaelrr.d.baozistore.repository.PedidoRepository;
import com.rafaelrr.d.baozistore.repository.ProdutoRepository;
import com.rafaelrr.d.baozistore.model.Produto;

@Service
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;
	private final ClienteRepository clienteRepository;

	public PedidoServiceImpl(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository,
			ClienteRepository clienteRepository) {
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	@Override
	public void insert(Pedido pedido) {

		Cliente buscarCliente = clienteRepository.findById(pedido.getCliente().getId())
				.orElseThrow(() -> new RuntimeException("Erro ao encontrar Cliente"));

		Produto buscarProduto = produtoRepository.findById(pedido.getProduto().getId())
				.orElseThrow(() -> new RuntimeException("Erro ao encontrar produto"));

		// Lógica para verificar se tem estoque do produto
		if (buscarProduto.getEstoque() <= 0) {
			throw new RuntimeException("Produto indisponível no estoque");
		}

		// Lógica para verificar se a quantidade é maior que o estoque
		if (pedido.getQuantidade() > buscarProduto.getEstoque()) {
			throw new RuntimeException("Produto indisponível no estoque");
		}

		// atualizar estoque do produto
		buscarProduto.setEstoque(buscarProduto.getEstoque() - pedido.getQuantidade());

		produtoRepository.save(buscarProduto);
		pedido.setCliente(buscarCliente);
		pedido.setProduto(buscarProduto);
		pedidoRepository.save(pedido);
	}

	@Transactional
	@Override
	public void update(Pedido pedido) {
		Pedido pedidoUpdate = pedidoRepository.findById(pedido.getId())
				.orElseThrow(() -> new RuntimeException("Erro ao encontrar pedido"));

		Produto buscarProduto = produtoRepository.findById(pedido.getProduto().getId())
				.orElseThrow(() -> new RuntimeException("Erro ao encontrar produto"));

		pedidoUpdate.setProduto(buscarProduto);
		pedidoUpdate.setQuantidade(pedido.getQuantidade());

		pedidoRepository.save(pedidoUpdate);

	}

	@Override
	public Pedido findId(Long id) {

		return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	}

	@Override
	public List<Pedido> findAll() {

		List<Pedido> pedidos = pedidoRepository.findAll();
		return pedidos;
	}

	@Override
	public void delete(Long id) {

		Pedido pedidoDelete = pedidoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		pedidoRepository.delete(pedidoDelete);

	}

}
