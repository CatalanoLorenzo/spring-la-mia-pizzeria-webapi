package org.java.app.db.pojo.repo;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
	public List<Pizza> findByNomeContaining(String nome);
}