package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.controller.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pizze")
public class PizzaRestController {
    
    @Autowired
    PizzaRepository pizzaRepository;

    @GetMapping
    public ResponseEntity<List<Pizza>> index(@RequestParam(name = "keyword", required = false) String keyword) {

        List<Pizza> pizze;

        if (keyword != null && !keyword.isEmpty() && !keyword.isBlank()) {
            pizze = pizzaRepository.findByNome(keyword);
        } else {
            pizze = pizzaRepository.findAll();
        }

        if (pizze.size() == 0) {
            return new ResponseEntity<List<Pizza>>(pizze, HttpStatus.NO_CONTENT );
        } else {
            return new ResponseEntity<List<Pizza>>(pizze, HttpStatus.OK);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> get(@PathVariable Integer id) {

        Optional<Pizza> pizzOptional = pizzaRepository.findById(id);

        if (pizzOptional.isPresent()) {
            return new ResponseEntity<Pizza>(pizzOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pizza> create(@Valid  @RequestBody Pizza pizza) {

        pizzaRepository.save(pizza);

        return new ResponseEntity<Pizza>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@Valid @RequestBody Pizza pizza, @PathVariable Integer id) {

        if (pizzaRepository.findById(id).isPresent()) {
            pizza.setId(id);
            return new ResponseEntity<Pizza>(pizzaRepository.save(pizza), HttpStatus.OK);
        } else {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
        
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);

        if (pizzaRepository.findById(id).isPresent()) {
            pizzaRepository.delete(pizzaOptional.get());
            return new ResponseEntity<Pizza>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
    }
}
