package org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.controller;

import java.util.Optional;

import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.model.Offerta;
import org.lessons.java.wdpt6.spring_la_mia_pizzeria_crud.repository.OffertaRepository;
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
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
    
    @Autowired
    private OffertaRepository offertaRepository;

    @PostMapping
    public String store(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("offerta", formOfferta);
            return "/offerte/create";
        }
        
        offertaRepository.save(formOfferta);    
        return "redirect:/pizze";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {

        Optional<Offerta> offertaOptional = offertaRepository.findById(id);
        if (offertaOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non ci sono Offerte con Id: " + id);

        model.addAttribute("offerta", offertaOptional.get());
        return "offerte/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute(name = "offerta") Offerta formOfferta, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offerte/edit";
        }

        offertaRepository.save(formOfferta);
        return "redirect:/pizze ";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {

        Optional<Offerta> offertaOptional = offertaRepository.findById(id);
        if (offertaOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non ci sono Offerte con Id: " + id);

        offertaRepository.delete(offertaOptional.get());
        return "redirect:/pizze";
    }
}
