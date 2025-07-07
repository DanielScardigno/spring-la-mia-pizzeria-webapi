package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Utente;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Utente> utente = utenteRepository.findByEmail(email);

        if (utente.isPresent()) {
            return new DatabaseUserDetails(utente.get());
        } else {
            throw new UsernameNotFoundException("Email: " + email + " non presente nel Database");
        }
    }
}
