package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.PizzaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzaList = repository.findAll();
        

        model.addAttribute("pizzaList", pizzaList);

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        Optional<Pizza> result = repository.findById(id);

        model.addAttribute("pizza", result.orElse(null));
        return "pizzas/show";
    }

    // @GetMapping("/searchByName")
    // public String searchIndex(Model model, @RequestParam(name = "name") String name) {

    //     List<Pizza> pizzaList = repository.findByNameContaining(name);

    //     model.addAttribute("pizzaList", pizzaList);

    //     return "pizzas/index";

    // }
    @GetMapping("/searchByName")
    public String searchIndex(Model model, @RequestParam(name = "query") String query) {

        List<Pizza> pizzaList = repository.findByNameContainingOrDescriptionContaining(query, query);

        model.addAttribute("pizzaList", pizzaList);

        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";

    }

    @PostMapping("/create")
     public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }
        repository.save(formPizza);
        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {

        model.addAttribute("pizza",repository.findById(id).get());

        return "pizzas/edit";
    }
    
    @PostMapping("/edit/{id}")
     public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }
        repository.save(formPizza);
        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
       repository.deleteById(id);
        
        return "redirect:/pizze";
    }
    
}
