package com.ever.br.api.control.gamer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableRabbit
@SpringBootApplication
public class ApiControlGamerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiControlGamerApplication.class, args);
	}

}
