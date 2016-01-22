package de.eichstaedt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("de.eichstaedt")
public class FamilyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyappApplication.class, args);
		
	}
}
