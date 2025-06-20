package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "pizze/show";
    }
}