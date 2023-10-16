package org.java.app.controller;
//-------------------------------------------------------------------------------------------------
import java.util.List;
import org.java.app.db.pojo.Pizza;
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
//-------------------------------------------------------------------------------------------------
@Controller //Specifico che questo file è un controller
@RequestMapping("/pizzas")//Dichiaro che la rotta principale inizia da "/pizzas"
public class PizzaController {
//------------------Iniezione Dipendenze Utili-----------------------------------------------
	@Autowired
	private PizzaService pizzaService;
//-------------------------------------------------------------------------------------------------
	@GetMapping
	public String getIndex(Model model, @RequestParam(required = false) String nome) {

		List<Pizza> pizzas = nome == null ? pizzaService.findAll() : pizzaService.findByName(nome);

		model.addAttribute("pizzas", pizzas);
		System.out.println("pizza: " + pizzas);

		return "pizza-index";
	}
//-------------------------------------------------------------------------------------------------
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {

		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);

		return "pizza-show";
	}
//-------------------------------------------------------------------------------------------------
	@GetMapping("/create")
	public String createPizza(Model model) {
		model.addAttribute("pizza", new Pizza());

		return "pizza-create";
	}

	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		System.out.println(bindingResult.getErrorCount());

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getErrorCount());

			return "pizza-create";
		}

		pizzaService.save(pizza);

		return "redirect:/pizzas";
	}
//-------------------------------------------------------------------------------------------------
	@GetMapping("/update/{id}")
	public String editPizza(@PathVariable int id, Model model) {
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute(pizza);
		return "pizza-update";
	}

	@PostMapping("/update/{id}")
	public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getErrorCount());

			return "pizza-create";
		}

		pizzaService.save(pizza);
		return "redirect:/pizzas";

	}
//-------------------------------------------------------------------------------------------------
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		Pizza pizza = pizzaService.findById(id);
		pizzaService.delete(pizza);
		return "redirect:/pizzas";
	}
}