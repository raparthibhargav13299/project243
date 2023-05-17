package com.stackroute.graphcommandservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class GraphcommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphcommandServiceApplication.class, args);
	}

}
