package www.fym.springbootexample.testApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAConfig {

	@Bean
	public TestA getTestA() {
		TestA testA = new TestA();
		return testA;
	}
}
