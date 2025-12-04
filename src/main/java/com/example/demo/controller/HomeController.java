package com.example.demo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.repository.CoffeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	@GetMapping("/page2")
	public String index() {
		return "page2"; // src/main/resources/templates/page2.html を返す
	}

	@GetMapping("/login")
	public String login() {
		return "login"; // src/main/resources/templates/login.html を返す
	}

	@GetMapping("/user")
	public String user() {
		return "user"; // src/main/resources/templates/user.html を返す
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin"; // src/main/resources/templates/admin.html を返す
	}

	private final ApplicationContext appContext;

	@GetMapping("/index")
	public String showList(Model model) {
		CoffeeRepository repository = (CoffeeRepository) appContext.getBean("coffeeRepository");
		model.addAttribute("toString", this.toString());
		model.addAttribute("allCoffee", repository.findAll());
		return "index";
	}
}
