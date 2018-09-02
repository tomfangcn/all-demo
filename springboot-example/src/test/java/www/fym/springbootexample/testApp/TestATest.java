package www.fym.springbootexample.testApp;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { TestAConfig.class, UserConfig.class, })
public class TestATest {

	// @Autowired
	// User user;

	@Test
	public void testSayHello() {
		// assertNotNull(user.getTesta());
		// user.getTesta().sayHello();

		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		System.out.println(bc.encode("jean"));
	}

}
