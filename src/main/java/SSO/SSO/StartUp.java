package SSO.SSO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan("SSO.SSO.controller")
public class StartUp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StartUp.class, args);
	}
}