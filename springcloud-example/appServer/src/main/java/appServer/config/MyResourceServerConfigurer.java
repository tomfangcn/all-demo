package appServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class MyResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	// @Bean
	// public ResourceServerTokenServices tokenService() {
	// RemoteTokenServices tokenServices = new RemoteTokenServices();
	// tokenServices.setClientId("client");
	// tokenServices.setClientSecret("secret");
	// tokenServices.setCheckTokenEndpointUrl("http://localhost:8093/oauth/check_token");
	// return tokenServices;
	// }

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("user_api");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/test/**").hasRole("GUEST");
	}
	// OAuth2ClientContextFilter
	// @Bean
	// public AccessTokenConverter accessTokenConverter() {
	// return new DefaultAccessTokenConverter();
	// }
	// OAuth2AuthenticationProcessingFilter
	// FilterSecurityInterceptor
}
