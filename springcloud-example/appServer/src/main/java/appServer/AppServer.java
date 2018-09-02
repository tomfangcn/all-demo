package appServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class AppServer {

	// @Bean
	// @LoadBalanced
	// public RestTemplate restTemplate() {
	// return new RestTemplate();
	// }

	@LoadBalanced
	@Bean
	public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
		return factory.getUserInfoRestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppServer.class, args);
	}
	// OAuth2AuthenticationProcessingFilter
}
