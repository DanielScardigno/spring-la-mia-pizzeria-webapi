package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offerte")
public class Offerta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il titolo non può essere vuoto o contenere solo spazi")
    private String titolo;

    @NotNull(message = "Valorizzare la data di inizio dell'offerta")
    @FutureOrPresent(message = "La data di inizio non può essere nel passato")
    private LocalDate dataDiInizio;

    @NotNull(message = "Valorizzare la data di fine dall'offerta")
    @FutureOrPresent(message = "La data di fine non può essere nel passato")
    private LocalDate dataDiFine;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    @JsonBackReference
    private Pizza pizza;

    // Getter e Setter

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataDiInizio() {
        return this.dataDiInizio;
    }

    public void setDataDiInizio(LocalDate dataDiInizio) {
        this.dataDiInizio = dataDiInizio;
    }

    public LocalDate getDataDiFine() {
        return this.dataDiFine;
    }

    public void setDataDiFine(LocalDate dataDiFine) {
        this.dataDiFine = dataDiFine;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}