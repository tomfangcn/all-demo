package authserver.password.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import authserver.password.provider.MyAuthProvider;

@Configuration
@EnableWebSecurity
public class OAuth2Config extends WebSecurityConfigurerAdapter {

	@Autowired
	MyAuthProvider myAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()//
				// .loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()//
				.and().authorizeRequests().anyRequest().authenticated();
	}

	// 配置内存模式的用户
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("jean")
				.password("$2a$10$ERI05k9eIeBzkaQQFfJb9.b9i7NhL5E68/NmzsFs7Tb9Wml7gWzsu").authorities("USER").build());
		manager.createUser(User.withUsername("jean2").password("jean").authorities("USER").build());
		return manager;
	}

	// @Override
	// public void configure(WebSecurity web) throws Exception {
	// web.ignoring().antMatchers("/favor.ico");
	// }

	@Bean
	public static PasswordEncoder passwordEncoder() {
		// return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuthProvider);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
