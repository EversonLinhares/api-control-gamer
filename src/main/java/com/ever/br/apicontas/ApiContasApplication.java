package com.ever.br.apicontas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiContasApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiContasApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
