package com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.TodoItemRepository;
import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.User;
import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.UserRepository;

@Controller
@RequestMapping("/")
public class UserController {
	   @Autowired
	    private UserRepository userRepo;
	
	 @GetMapping("register")
	 public String showRegistrationForm(Model model) {
		    model.addAttribute("user", new User());
		     
		    return "register";

	 }
	 @PostMapping("register_success")
	 public String processRegister(User user) {
			/*
			 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
			 * encodedPassword = passwordEncoder.encode(user.getPassword());
			 * user.setPassword(encodedPassword);
			 */
	
	      
	userRepo.save(user);
	      
	     return "register_success";
	 }

	 @GetMapping("login")
	 public String showLoginForm(Model model) {
		    model.addAttribute("user", new User());
		     
		    return "login";

	 }
}
