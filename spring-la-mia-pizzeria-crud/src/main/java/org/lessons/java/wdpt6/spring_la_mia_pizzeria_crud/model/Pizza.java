package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model;

import java.util.List;
import java.util.Locale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizze")
public class Pizza {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@NotBlank(message = "Il nome deve avere almeno un carattere")
    private String nome;

	@Lob
	@NotBlank(message = "La descrizione deve avere almeno un carattere")
    private String descrizione;

	@Lob
	@NotBlank(message = "Aggiungere un'immagine")
    private String fotoUrl;

	@NotNull(message = "Aggiungere il prezzo")
	@Min(value = 0, message = "Il prezzo non può essere inferiore a 0€")
    private double prezzo;

	@OneToMany(mappedBy = "pizza")
	private List<Offerta> offerte;

	@ManyToMany
	@JoinTable(
		name = "ingrediente_pizza",
		joinColumns = @JoinColumn(name = "pizza_id"),
		inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
	) 
	private List<Ingrediente> ingredienti;

	// Getter e Setter

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFotoUrl() {
		return this.fotoUrl;
	}

	public void setFotoUrl(String foto) {
		this.fotoUrl = foto;
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public List<Offerta> getOfferte() {
		return this.offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}

	public List<Ingrediente> getIngredienti() {
        return this.ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

	// Metodi

	public String getPrezzoEuro() {
		return String.format(Locale.ITALY, "%,.2f€", this.getPrezzo());
	}
}