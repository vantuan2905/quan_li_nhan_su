package tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControll {
	@RequestMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}
}
