package com.solebac.alglog.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solebac.alglog.api.controller.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/clientes")
	public List<Cliente> lista() {
		Cliente c1 = new Cliente(1L, "Flavio", "solebac@hotmail.com", "1234-12355");
		Cliente c2 = new Cliente(2L, "Felipe", "solebacfrs@hotmail.com", "1234-12355");
		return Arrays.asList(c1, c2);
	}
}
