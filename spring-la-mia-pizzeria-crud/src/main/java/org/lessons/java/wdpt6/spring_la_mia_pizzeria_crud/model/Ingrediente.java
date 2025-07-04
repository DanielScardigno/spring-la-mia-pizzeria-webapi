package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredienti")
public class Ingrediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @NotBlank(message = "L'URL della foto non può essere vuoto o composto solo da spazi")
    private String fotoUrl;

    @NotBlank(message = "Il nome dell'ingrediente non può essere vuoto o composto solo da spazi")
    private String nome;

    @Lob
    private String descrizione;

    @ManyToMany(mappedBy = "ingredienti")
    private List<Pizza> pizze;

    // Getter e Setter

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFotoUrl() {
        return this.fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
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

    public List<Pizza> getPizze() {
        return this.pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }
}
