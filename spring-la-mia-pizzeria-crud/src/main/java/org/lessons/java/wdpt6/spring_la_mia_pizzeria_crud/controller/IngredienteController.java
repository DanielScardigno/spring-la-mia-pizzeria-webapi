package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Ingrediente;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/ingredienti")
public class IngredienteController {
    
    @Autowired
    IngredienteRepository ingredienteRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Ingrediente> ingredienti;

        if (keyword != null && !keyword.isEmpty()) {
            ingredienti = ingredienteRepository.findByNome(keyword);
        } else {
            ingredienti = ingredienteRepository.findAll();
        }

        model.addAttribute("ingredienti", ingredienti);
        model.addAttribute("keyword", keyword);
        return "ingredienti/index";
    }

    @GetMapping("{id}")
    public String view(@PathVariable Integer id, Model model) {

        Optional<Ingrediente> ingredienteOptional = ingredienteRepository.findById(id);
        if (ingredienteOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non ci sono ingredienti con id: " + id);

        model.addAttribute("ingrediente", ingredienteOptional.get());
        return "ingredienti/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienti/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredienti/create";
        }

        ingredienteRepository.save(formIngrediente);
        return "redirect:/ingredienti";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        Optional<Ingrediente> ingredienteOptional = ingredienteRepository.findById(id);
        if (ingredienteOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non ci sono ingredienti con id: " + id);

        model.addAttribute("ingrediente", ingredienteRepository.findById(id).get());
        return "ingredienti/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredienti/edit";
        }

        ingredienteRepository.save(formIngrediente);
        return "redirect:/ingredienti";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {

        Ingrediente ingredienteDaEliminare = ingredienteRepository.findById(id).get();
        for (Pizza pizzaCollegata : ingredienteDaEliminare.getPizze()) {
            pizzaCollegata.getIngredienti().remove(ingredienteDaEliminare);
        }
        ingredienteRepository.deleteById(id);
        return "redirect:/ingredienti";
    }
}
