package org.java.app.db.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Offerta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@NotNull
	private LocalDate dataInizio;
	@Column(nullable = false)
	@NotNull
	private LocalDate dataFine;
	@NotBlank
	private String titolo;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(nullable = false)
	private Pizza pizza;

	public Offerta() {
	};

	public Offerta(LocalDate dataInizio, LocalDate dataFine, String titolo,Pizza pizza) {
		setDataFine(dataFine);
		setDataInizio(dataInizio);
		setTitolo(titolo);
		setPizza(pizza);
	}

	public Pizza getPizza() {
		return pizza;
	}
	public int getId() {
		return id;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
}
