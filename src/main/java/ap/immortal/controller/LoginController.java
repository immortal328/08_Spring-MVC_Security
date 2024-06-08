package ap.immortal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showMyLoginForm")
	public String showLoginForm() {
		return "plain-login";
	}

}
