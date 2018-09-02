package www.fym.springbootexample.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class OAuthWebConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()//
				.and().authorizeRequests().anyRequest().authenticated();
	}

	// 配置内存模式的用户
	// @Bean
	// @Override
	// protected UserDetailsService userDetailsService() {
	// InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	// manager.createUser(User.withUsername("jean").password("jean").authorities("USER").build());
	// manager.createUser(User.withUsername("jean2").password("jean").authorities("USER").build());
	// return manager;
	// }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favor.ico");
	}
}
