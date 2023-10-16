package org.java.app.controller;

import org.java.app.db.pojo.Offerta;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.service.OffertaService;
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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaController {
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private OffertaService offertaService;
	
	@GetMapping("{id}/nuovaOfferta")
	public String create(@PathVariable int id, Model model) {
		model.addAttribute("offerta", new Offerta());
		model.addAttribute("pizza", pizzaService.findById(id));
		
		return "offerta-create";
	}
	
	@PostMapping("{pizza_id}/nuovaOfferta")
	public String store(@PathVariable("pizza_id") int id, @Valid @ModelAttribute Offerta offerta, BindingResult bindingResult, Model model) {
		offerta.setPizza(pizzaService.findById(id));
		
		offertaService.OffertaSave(offerta);
		
		return "redirect:/pizzas/" + id;
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Offerta offerta = offertaService.findById(id);
		model.addAttribute("offerta", offerta);
		model.addAttribute("pizza", pizzaService.findById(offerta.getPizza().getId()));
		
		return "offerta-edit";
	}
	
	@PostMapping("edit/{id}")
	public String update(@PathVariable int id, @Valid @ModelAttribute Offerta offerta, BindingResult bindingResult) {
		Offerta offertaDiPartenza = offertaService.findById(id);
		Pizza pizza = offertaDiPartenza.getPizza();
		
		offerta.setPizza(pizza);
		
		offertaService.OffertaSave(offerta);
		
		return "redirect:/pizzas/" + pizza.getId();
	}
	@PostMapping("delete/{id}")
	public String delete(@PathVariable int id) {
		Offerta offerta = offertaService.findById(id);
		Pizza pizza = offerta.getPizza();
		offertaService.offertaDelete(offerta);
		return "redirect:/pizzas/" + pizza.getId();
	}
}
