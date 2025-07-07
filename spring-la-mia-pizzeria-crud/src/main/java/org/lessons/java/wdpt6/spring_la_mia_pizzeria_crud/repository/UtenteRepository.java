package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository;

import java.util.Optional;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmail(String email);
}
