package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    
    public List<Pizza> findByNome(String nome);
}