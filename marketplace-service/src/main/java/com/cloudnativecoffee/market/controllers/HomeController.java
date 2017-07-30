/**
 * 
 */
package com.cloudnativecoffee.market.controllers;

import com.cloudnativecoffee.market.model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the Index/Home page
 * @author lshannon
 *
 */
@Controller
public class HomeController {

	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("login", new Login());
		return "login";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		return "index";
	}

}
