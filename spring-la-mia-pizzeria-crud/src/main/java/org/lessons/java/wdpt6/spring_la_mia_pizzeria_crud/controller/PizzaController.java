package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Pizza> pizze;

        if (keyword != null && !keyword.isEmpty()) {
            pizze = pizzaRepository.findByNome(keyword);
        } else {
            pizze = pizzaRepository.findAll();
        }

        model.addAttribute("pizze", pizze);
        model.addAttribute("keyword", keyword);
        return "pizze/index";
    }

    @GetMapping("/{id}")
    public String view(Model model, @PathVariable("id") Integer id) {

        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non ci sono pizze con Id: " + id);

        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "pizze/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());

        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "/pizze/create";
        }
        
        pizzaRepository.save(formPizza);
        return "redirect:/pizze";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable Integer id) {

        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non ci sono pizze con Id: " + id);

        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "pizze/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "/pizze/edit";
        }
        
        pizzaRepository.save(formPizza);
        return "redirect:/pizze";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);
        return "redirect:/pizze";
    }
}