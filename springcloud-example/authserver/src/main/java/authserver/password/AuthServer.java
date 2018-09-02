package authserver.password;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthServer {

	// @Bean
	// @LoadBalanced
	// public RestTemplate restTemplate() {
	// return new RestTemplate();
	// }

	// @LoadBalanced
	// @Bean
	// public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory
	// factory) {
	// return factory.getUserInfoRestTemplate();
	// }

	public static void main(String[] args) {
		SpringApplication.run(AuthServer.class, args);
	}

}
