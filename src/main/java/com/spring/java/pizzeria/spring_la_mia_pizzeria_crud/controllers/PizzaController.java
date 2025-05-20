package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.PizzaRepository;

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

}
