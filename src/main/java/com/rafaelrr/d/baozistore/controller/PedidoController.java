package com.rafaelrr.d.baozistore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelrr.d.baozistore.model.Pedido;
import com.rafaelrr.d.baozistore.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	private final PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Pedido pedido) {
		pedidoService.insert(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Pedido pedido) {
		pedido.setId(id);
		pedidoService.update(pedido);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		pedidoService.findId(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {

		return ResponseEntity.ok(pedidoService.findAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pedidoService.delete(id);

		return ResponseEntity.noContent().build();
	}

}
