package com.school.controllerVista;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginViewController {
	
	
	  @GetMapping("/login") // Mantener este mapeo como está
	    public String loginPage(Model model) {
	        return "login";
	    }
	}