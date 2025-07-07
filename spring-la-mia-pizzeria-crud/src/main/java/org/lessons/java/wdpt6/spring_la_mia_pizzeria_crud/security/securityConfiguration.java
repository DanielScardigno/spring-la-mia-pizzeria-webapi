package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfiguration {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests -> requests
            .requestMatchers("/pizze/create", "/pizze/*/edit", "/pizze/*/create", "/pizze/*/delete").hasAuthority("ADMIN")
            .requestMatchers("/offerte/create", "/offerte/*/edit", "/offerte/*/delete").hasAuthority("ADMIN")
            .requestMatchers("/ingredienti/create", "/ingredienti/*/edit", "/ingredienti/*/delete").hasAuthority("ADMIN")
            .requestMatchers("/pizze", "/pizze/**", "/ingredienti", "/ingredienti/**").hasAuthority("USER")
            .requestMatchers("/**").permitAll())
            .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    DatabaseUserDetailsService userDetailService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}