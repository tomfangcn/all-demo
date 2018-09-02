package authserver.password.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/default/user")
public class UserController {

	@RequestMapping("/principal")
	@ResponseBody
	public Principal getUser(Principal principal) {
		return principal;
	}

	@RequestMapping("/test")
	@ResponseBody
	public String getUser() {
		return "1";
	}
}
