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
		//Codigo JPQL
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}
}
