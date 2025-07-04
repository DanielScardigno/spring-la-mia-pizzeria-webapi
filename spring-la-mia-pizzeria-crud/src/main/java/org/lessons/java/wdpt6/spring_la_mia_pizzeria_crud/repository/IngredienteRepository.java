package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer>{

    public List<Ingrediente> findByNome(String nome);
}
