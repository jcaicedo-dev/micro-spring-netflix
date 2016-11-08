package com.devstepsblog.greetings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GreetingsServiceConfig {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GreetingsServiceConfig.class, args);
	}
	
}
