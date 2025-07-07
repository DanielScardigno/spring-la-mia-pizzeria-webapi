package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ruoli")
public class Ruolo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome non può essere vuoto o composto solo da spazi")
    private String nome;

    @ManyToMany(mappedBy = "ruoli")
    private List<Utente> utenti;

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
    
    public List<Utente> getUtenti() {
        return this.utenti;
    }

    public void setNome(List<Utente> utenti) {
        this.utenti = utenti;
    }
}