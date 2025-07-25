package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Ruolo;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails{

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(Utente utente) {
        this.id = utente.getId();
        this.username = utente.getEmail();
        this.password = utente.getPassword();

        this.authorities = new HashSet<GrantedAuthority>();

        for (Ruolo ruolo : utente.getRuoli()) {
            this.authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
        }
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}