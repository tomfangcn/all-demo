package authserver.password.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/mylogin")
	public String userLogin() {

		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError() {
		return "error";

	}

	// @RequestMapping("/login/form")
	// public String loginForm() {
	// try {
	// OAuthAuth2Request oauthResponse = OAuthClientRequest
	// .authorizationLocation(ConstantKey.OAUTH_CLIENT_AUTHORIZE)
	// .setResponseType(OAuth.OAUTH_CODE)
	// .setClientId(ConstantKey.OAUTH_CLIENT_ID)
	// .setRedirectURI(ConstantKey.OAUTH_CLIENT_CALLBACK)
	// .setScope(ConstantKey.OAUTH_CLIENT_SCOPE)
	// .buildQueryMessage();
	// return "redirect:"+oauthResponse.getLocationUri();
	// } catch (OAuthSystemException e) {
	// e.printStackTrace();
	// }
	// return "redirect:/home";
	//
	// }
}
