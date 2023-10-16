package org.java.app.controller;

import java.util.List;

import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.pojo.Offerta;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.service.IngredienteService;
import org.java.app.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")

public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public String getIndex(Model model, @RequestParam(required = false) String nome) {

		List<Ingrediente> ingredienti = ingredienteService.findAll();

		model.addAttribute("ingredienti", ingredienti);
		System.out.println("ingredienti: " + ingredienti);

		return "ingredienti-index";
	}

	@GetMapping("/create")
	public String ingredientiCreate(Model model) {

		List<Pizza> pizzas = pizzaService.findAll();
		Ingrediente ingrediente = new Ingrediente();

		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("pizze", pizzas);

		return "ingrediente-create";
	}

	@PostMapping("/create")
	public String ingredientiStore(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult,
			Model model) {

		System.out.println("Nuovo ingrediente:\n" + ingrediente);
		System.out.println("ingrediente pizze:\n" + ingrediente.getPizze());

		ingredienteService.save(ingrediente);

		for (Pizza pizza : ingrediente.getPizze()) {

			pizza.addIngredienti(ingrediente);
			pizzaService.save(pizza);
		}
		return "redirect:/ingredienti";
	}

	@GetMapping("/update/{id}")
	public String ingredientiEdit(@PathVariable int id, Model model) {

		model.addAttribute("ingrediente", ingredienteService.findById(id));
		model.addAttribute("pizze", pizzaService.findAll());

		return "ingrediente-create";
	}

	@PostMapping("/update/{id}")
	public String ingredientiUpdate(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult,
			Model model) {
		System.out.println("Nuovo ingrediente:\n" + ingrediente);
		System.out.println("ingredienti pizze:\n" + ingrediente.getPizze());
		ingredienteService.save(ingrediente);
		List<Pizza> pizzas = pizzaService.findAll();
		for (Pizza pizza : pizzas) {
			
			System.out.println("bef: " + pizza + "\ning: " + pizza.getIngredienti());
			if (ingrediente.hasPizze(pizza)) 
				pizza.addIngredienti(ingrediente);
			else pizza.getIngredienti().remove(ingrediente);
			System.out.println("aft: " + pizza + "\ning: " + pizza.getIngredienti());
			
			pizzaService.save(pizza);
		}
		

		return "redirect:/ingredienti";
	}
	@PostMapping("delete/{id}")
	public String ingredienteDelete(@PathVariable int id) {
		Ingrediente ingrediente = ingredienteService.findById(id);
		List<Pizza> pizze = ingrediente.getPizze();
		ingredienteService.ingredienteDelete(ingrediente);
		for (Pizza pizza : pizze) {
		pizza.getIngredienti().remove(ingrediente);
		}
		return "redirect:/ingredienti/";
	}
}
