package c14.NoCountry;

import c14.NoCountry.Service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NoCountryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoCountryApplication.class, args);
	}

}
