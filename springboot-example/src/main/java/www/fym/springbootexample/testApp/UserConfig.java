package www.fym.springbootexample.testApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class UserConfig {

	@Bean
	public User getUser(TestA testa) {
		User user = new User(testa);
		return user;
	}
}
