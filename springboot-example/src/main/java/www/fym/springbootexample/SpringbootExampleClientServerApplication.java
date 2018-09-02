// package www.fym.springbootexample;
//
// import java.security.Principal;
// import java.util.LinkedHashMap;
// import java.util.Map;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.oauth2.client.OAuth2ClientContext;
// import
// org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
// import
// org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
// import
// org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
// import
// org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
// import
// org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// @SpringBootApplication
// @EnableOAuth2Client
// @EnableAuthorizationServer
// @RestController
// @Order(2)
// public class SpringbootExampleClientServerApplication extends
// WebSecurityConfigurerAdapter {
//
// @Autowired
// OAuth2ClientContext oauth2ClientContext;
//
// // @RequestMapping("/user")
// // public Principal user(Principal principal) {
// // return principal;
// // }
//
// @RequestMapping({ "/user", "/me" })
// public Map<String, String> user(Principal principal) {
// Map<String, String> map = new LinkedHashMap<>();
// map.put("name", principal.getName());
// return map;
// }
//
// @RequestMapping("/")
// public String home(Principal user) {
// return "Hello " + user.getName();
// }
//
// public static void main(String[] args) {
// new SpringApplicationBuilder(SpringbootExampleClientServerApplication.class)
// .properties("spring.config.name=client").run(args);
// }
//
// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**",
// "/webjars/**").permitAll().anyRequest()
// .authenticated().and().exceptionHandling()
// .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));
// }
//
// @Configuration
// @EnableResourceServer
// protected static class ResourceServerConfiguration extends
// ResourceServerConfigurerAdapter {
// @Override
// public void configure(HttpSecurity http) throws Exception {
// http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
// }
// }
// }
