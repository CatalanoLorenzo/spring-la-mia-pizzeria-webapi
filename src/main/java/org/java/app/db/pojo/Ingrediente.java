package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String nomeIngrediante;

	@ManyToMany(mappedBy = "ingredientis")
	@JsonBackReference

	private List<Pizza> pizze;

	public Ingrediente() {
	};

	public Ingrediente(String nomeIngrediente, Pizza... pizze) {
		setNomeIngrediante(nomeIngrediente);
		setPizze(Arrays.asList(pizze));
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeIngrediante() {
		return nomeIngrediante;
	}

	public void setNomeIngrediante(String nomeIngrediante) {
		this.nomeIngrediante = nomeIngrediante;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

	public boolean hasPizze(Pizza pizza) {

		if (getPizze() == null)
			return false;

		for (Pizza p : getPizze())
			if (p.getId() == pizza.getId())
				return true;

		return false;
	}

	public void addPizze(Pizza... pizze) {
		getPizze().addAll(Arrays.asList(pizze));
	}

	public void removePizze(Pizza... pizze) {

		getPizze().removeAll(Arrays.asList(pizze));
	}

	@Override
	public String toString() {

		return "[" + getId() + "] " + getNomeIngrediante() + "\n";
	}

	@Override
	public int hashCode() {
		
		return getId();
	}
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Ingrediente)) return false;
		
		Ingrediente incomingIng = (Ingrediente) obj;
		
		return getId() == incomingIng.getId();
	}
}
