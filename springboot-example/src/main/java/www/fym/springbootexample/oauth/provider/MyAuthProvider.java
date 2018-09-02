package www.fym.springbootexample.oauth.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class MyAuthProvider implements AuthenticationProvider {
	private static final Logger log = LoggerFactory.getLogger(MyAuthProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		log.debug("自定义provider调用");

		List<String> auths = new ArrayList<>();
		auths.add("ROLE_ADMIN");
		auths.add("ROLE_GUEST");
		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities = auths.stream().map(auth -> new SimpleGrantedAuthority(auth)).collect(Collectors.toList());

		// 返回一个Token对象表示登陆成功
		return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
				authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
