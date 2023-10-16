package org.java.app;




import java.time.LocalDate;

import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.pojo.Offerta;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.service.IngredienteService;
import org.java.app.db.service.OffertaService;
import org.java.app.db.service.PizzaService;
import org.java.app.db.service.RoleService;
import org.java.app.db.service.UserService;
import org.java.app.mvc.auth.pojo.Role;
import org.java.app.mvc.auth.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private OffertaService offertaService;
	@Autowired
	private IngredienteService ingredienteService; 
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Ingrediente i1 = new Ingrediente("pomodoro");
		Ingrediente i2 = new Ingrediente("mozzorella" );
		Ingrediente i3 = new Ingrediente("origano");
		Ingrediente i4 = new Ingrediente("wrustell");
		Ingrediente i5 = new Ingrediente("patatine");
		
		ingredienteService.save(i1);
		ingredienteService.save(i2);
		ingredienteService.save(i3);
		ingredienteService.save(i4);
		ingredienteService.save(i5);

		

		Pizza pizza1 = new Pizza("Margherita", "pomodoro,mozzarella,basilico",
				"https://menu.sluurpy.it/foto-g/57579/3403689.jpg", 3.50F,i1,i2);
		Pizza pizza2 = new Pizza("Marinara", "pomodoro,origano",
				"https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/vimdb/83535_751-0-4864-4864.jpg",
				4.5F,i1,i3);
		Pizza pizza3 = new Pizza("viustel e patataine", "mozzarella ,viustell,patataine",
				"https://th.bing.com/th/id/R.4069db1e7bfd020ee68342db2a3fe58a?rik=mfkqyUrXh2KbCg&riu=http%3a%2f%2fwww.scattidigusto.it%2fwp-content%2fuploads%2f2015%2f11%2fpizza-wurstel-e-patatine.jpg&ehk=fyLPxmGBNht9evLBGSMZ1C%2fEO1twdwnw%2b%2f23ZS4RW9Q%3d&risl=&pid=ImgRaw&r=0",
				5.0F,i4,i5);

		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);

		System.out.println("Insert OK!");
		Offerta offerta1 = new Offerta (LocalDate.now(), LocalDate.parse("2023-12-31"),"offertapizza1",pizza1);
		Offerta offerta2 = new Offerta (LocalDate.now(), LocalDate.parse("2023-12-31"),"offertapizza2",pizza2);
		Offerta offerta3 = new Offerta (LocalDate.now(), LocalDate.parse("2023-12-31"),"offertapizza3",pizza3);
		offertaService.OffertaSave(offerta1);
		offertaService.OffertaSave(offerta2);
		offertaService.OffertaSave(offerta3);
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		
		roleService.save(admin);
		roleService.save(user);
		
		final String pwsAdmin = new BCryptPasswordEncoder().encode("pws");
		final String pwsUser = new BCryptPasswordEncoder().encode("pws");
		
		User guybrushAdmin = new User("guybrushAdmin", pwsAdmin, admin, user);
		User guybrushUser = new User("guybrushUser", pwsUser, user);
		
		userService.save(guybrushAdmin);
		userService.save(guybrushUser);
		

	}
}