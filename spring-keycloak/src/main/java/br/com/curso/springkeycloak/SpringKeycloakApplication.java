package br.com.curso.springkeycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.validation.constraints.NotBlank;

@SpringBootApplication
public class SpringKeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKeycloakApplication.class, args);
	}

}
