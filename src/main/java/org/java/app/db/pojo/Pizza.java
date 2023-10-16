package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.java.app.api.PizzaDTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false, length = 80)
	@NotBlank(message = "campo obbligatorio ")
	@Length(min = 4, max = 80, message = "parola troppo lunga o troppo corta")
	private String nome;

	@Column(columnDefinition = "text")
	@Length(min = 10, max = 160, message = "parola troppo lunga o troppo corta")
	private String descrizione;

	@Column(nullable = false)
	@Positive(message = "numero deve essere maggiore di 0 ")
	private float prezzo;

	@Column(unique = true, length = 1000)
	private String foto;

	@OneToMany(mappedBy = "pizza")
	@JsonManagedReference
	private List<Offerta> offerte;

	@ManyToMany
	@JsonManagedReference
	private List<Ingrediente> ingredientis;

	public Pizza(PizzaDTO pizzaDto) {
		setNome(pizzaDto.getNome());
		setDescrizione(pizzaDto.getDescrizione());
		setFoto(pizzaDto.getFoto());
		setPrezzo(pizzaDto.getPrezzo());
	}

	public Pizza(String nome, String descrizione, String foto, float prezzo, Ingrediente... ingredientis) {
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
		setIngredienti(Arrays.asList(ingredientis));

	}

	public List<Offerta> getOfferte() {
		return offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredientis;
	}

	public void setIngredienti(List<Ingrediente> ingredientis) {
		this.ingredientis = ingredientis;
	}

	public boolean hasingredienti(Ingrediente ingrediente) {

		for (Pizza pizza : ingrediente.getPizze()) {
			if (pizza.getId()== this.getId()) {
				return true;
			}
			
		}
		return false;
	}

	public void addIngredienti(Ingrediente ingrediente) {

		getIngredienti().add(ingrediente);
	}

	public void removeIngredienti(Ingrediente ingrediente) {
		List<Ingrediente> listaIgredienti= getIngredienti();
		listaIgredienti.remove(ingrediente);
	}
	
	public void aggiornaTramiteApiPizzaDto(PizzaDTO  pizzaDto) {
		
		setNome(pizzaDto.getNome());
		setDescrizione(pizzaDto.getDescrizione());
		setFoto(pizzaDto.getFoto());
		setPrezzo(pizzaDto.getPrezzo());
	}

	@Override
	public String toString() {

		return "new Pizza";
}}