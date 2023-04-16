package com.ever.br.api.control.gamer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class ApiControlGamerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiControlGamerApplication.class, args);
	}

}
