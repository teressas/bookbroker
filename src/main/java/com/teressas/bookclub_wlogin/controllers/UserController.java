package com.teressas.bookclub_wlogin.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.teressas.bookclub_wlogin.models.LoginUser;
import com.teressas.bookclub_wlogin.models.User;
import com.teressas.bookclub_wlogin.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	// show form to register a new user and login a user
	@GetMapping("/")
	public String index(Model model, HttpSession session) { 
		if(session.getAttribute("userId")!= null) {
			return "redirect:/books";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	// process registration form
	@PostMapping("/register") 
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, HttpSession session, Model model) {
        userService.register(newUser, result);
        if(result.hasErrors()) {
        	model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("userId", newUser.getId());
        session.setAttribute("userName", newUser.getUserName());
        return "redirect:/books";
    }
	
	// login user and validate form
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getUserName());
        return "redirect:/books";
    }

	// page user goes to after registration and login
//	@GetMapping("/home")
//	public String home(HttpSession session) {
//		if(session.getAttribute("userId") == null ) {
//			return "redirect:/";
//		} else {
//			return "login.jsp"; 			
//		}
//	}
	
	 @GetMapping("/logout")
	    public String home(HttpSession session) {
	    	session.invalidate();
	    	return "redirect:/";
	    }


}
