package fr.dawan.gestionutilisateur.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dawan.gestionutilisateur.dto.UserDTO;
import fr.dawan.gestionutilisateur.service.UserService;
import fr.dawan.gestionutilisateur.service.exceptions.EmailAlreadyInUseException;
import fr.dawan.gestionutilisateur.service.exceptions.PassWordNotMatching;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private UserService service;


	@GetMapping("/register")
	public String signUp(Model model) {

		model.addAttribute("title", "Register");
		model.addAttribute("user", new UserDTO());

		return "register";
	}

	@PostMapping("/register/save")
	public String signUp(@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model) {

		try {
			if (result.hasErrors()) {
				model.addAttribute("userDto", userDto);
				return "register";
			} else {
				try {
					service.saveUser(userDto);
					model.addAttribute("success", "Register successfully!");
					return "register";
				} catch (PassWordNotMatching e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					model.addAttribute("passwordNotMatchExcpetion", e.getPasswordNotMatch());
					return "register";
				} catch (EmailAlreadyInUseException e) {
					e.printStackTrace();
					model.addAttribute("emailAlreadyInUseException", e.getEmailAlreadyUsed());
					return "register";
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errors", "The server has been wrong!");
			return "register";
		}



	}



	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("title", "Login");
		return "login";
	}

	@GetMapping("/index")
	public String home(Model model) {
		model.addAttribute("title", "Home page");
		return "index";
	}


	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		model.addAttribute("title", "Forgot Password");
		return "forgot-password";
	}

}
