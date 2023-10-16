package org.java.app.api;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Pizza;
import org.java.app.api.PizzaDTO;
import org.java.app.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("tuttelepizze")
	public ResponseEntity<List<Pizza>> getAllPizzaAPI(){
		
		List<Pizza> pizze = pizzaService.findAll();
		
		return new ResponseEntity<List<Pizza>>(pizze,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping
	public ResponseEntity<Integer> savePizzaAPI(
			@RequestBody PizzaDTO pizzaDto
		) {
		
		Pizza pizza = new Pizza(pizzaDto);
		
		pizzaService.save(new Pizza(pizzaDto));
		return new ResponseEntity<>(pizza.getId(), HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizzaAPI(
			@PathVariable int id
		) {
		
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		
		
		if (optionalPizza.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(optionalPizza.get(), HttpStatus.OK);
	}
	
	
	
	
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> updatePizzaAPI(
			@PathVariable int id,
			@RequestBody PizzaDTO pizzaDto
		) {
		
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		
		if (optionalPizza.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		Pizza pizza = optionalPizza.get();
		pizza.aggiornaTramiteApiPizzaDto(pizzaDto);
	
		try {
			
			pizzaService.save(pizza);
			
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizzaAPI(
			@PathVariable int id
		) {
		
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		
		if (optionalPizza.isEmpty()) {
			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		pizzaService.delete(optionalPizza.get());
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}		
}
