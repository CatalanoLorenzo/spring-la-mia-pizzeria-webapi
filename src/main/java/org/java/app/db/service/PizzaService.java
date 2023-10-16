package org.java.app.db.service;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;

	public void save(Pizza pizza) {

		pizzaRepo.save(pizza);
	}

	public List<Pizza> findAll() {

		return pizzaRepo.findAll();
	}

	public Optional<Pizza> findById(int id) {

		return pizzaRepo.findById(id);
	}

	public List<Pizza> findByName(String nome) {

		return pizzaRepo.findByNomeContaining(nome);
	}
	public void delete(Pizza pizza) {
		
	 pizzaRepo.delete(pizza);
	
}
}
