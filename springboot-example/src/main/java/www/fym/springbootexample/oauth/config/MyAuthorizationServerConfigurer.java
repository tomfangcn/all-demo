package www.fym.springbootexample.oauth.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import www.fym.springbootexample.oauth.provider.MyAuthProvider;

@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public AuthenticationProvider myAuthProvider() {
		return new MyAuthProvider();
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Arrays.asList(myAuthProvider()));
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// oauthServer.checkTokenAccess("isAuthenticated()");
		security.checkTokenAccess("permitAll()")//
				.tokenKeyAccess("permitAll()")//
				.allowFormAuthenticationForClients();

	}

	// @Bean
	// public ClientDetailsService clientDetails() {
	// return new JdbcClientDetailsService(dataSource);
	// }

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// clients.withClientDetails(clientDetails());

		clients.inMemory() // 使用in-memory存储
				.withClient("client")// client_id
				.secret("secret") // client_secret
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")//
				.authorities("USER")//
				.scopes("app") //
				.resourceIds("user_api") //
				.accessTokenValiditySeconds(600); // 允许的授权范围

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager());
		endpoints.tokenStore(tokenStore());
		// 配置TokenServices参数
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(endpoints.getTokenStore());
		tokenServices.setSupportRefreshToken(false);
		tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
		tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
		tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
		endpoints.tokenServices(tokenServices);
	}

}
