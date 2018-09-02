package appServer.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import appServer.entity.User;

@Controller
@RequestMapping("/test/user")
public class UserController {

	@Autowired
	private OAuth2RestTemplate rest;

	@RequestMapping("/principal")
	@ResponseBody
	public Principal getUser(Principal principal) {

		return principal;
	}

	@RequestMapping("/toauth/principal")
	@ResponseBody
	public String toAuthPrincipal() {
		String p = "权限不够";
		try {
			p = rest.postForObject("http://authServer/default/user/principal", null, String.class);

		} catch (UserRedirectRequiredException e) {
			System.out.println(p);
		}
		return p.toString();
	}

	@RequestMapping("/{id}")
	public String getUser(@PathVariable Integer id, Model model) {

		model.addAttribute("user", new User(id, "张三", 20, "中国广州"));
		return "/user/detail";
	}

	@RequestMapping("/list")
	public String listUser(Model model) {
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			userList.add(new User(i, "张三" + i, 20 + i, "中国广州"));
		}

		model.addAttribute("users", userList);
		return "/user/list";
	}
}
