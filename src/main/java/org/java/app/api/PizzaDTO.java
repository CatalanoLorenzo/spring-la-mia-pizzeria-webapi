package org.java.app.api;

public class PizzaDTO {

	private int id;
	
	private String nome;		
	private String descrizione;
	private float prezzo;
	private String foto;
	
	public PizzaDTO() { }
	public PizzaDTO(String nome) {
		
		setNome(nome);
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
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		
		return  "id: " + getId()
				+ "\nNome: " + getNome()
				+ "\nDescrizione: " + getDescrizione()
				+ "\nUrl Foto: " + getFoto()
				+ "\nPrezzo: " + getPrezzo();
	}
}
