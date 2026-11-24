package com.rafaelrr.d.baozistore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelrr.d.baozistore.model.Produto;
import com.rafaelrr.d.baozistore.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;

	public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	@Override
	public void insert(Produto produto) {

		if (produtoRepository.jaExisteEsseProduto(produto.getNome())) {
			throw new RuntimeException("O produto já existe");
		}

		produtoRepository.save(produto);

	}

	@Transactional
	@Override
	public void update(Produto produto) {
		Produto buscarProduto = findId(produto.getId());

		buscarProduto.setNome(produto.getNome());
		buscarProduto.setPreco(produto.getPreco());
		buscarProduto.setEstoque(produto.getEstoque());

		produtoRepository.save(buscarProduto);

	}

	@Override
	public Produto findId(Long id) {

		Produto buscaProduto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		return buscaProduto;
	}

	@Override
	public List<Produto> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		return produtos;
	}

	@Override
	public void delete(Long id) {
		Produto buscaProduto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		produtoRepository.delete(buscaProduto);

	}

}
