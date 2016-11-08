package com.devsteps.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ClientServiceConfig {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ClientServiceConfig.class, args);
	}

	
}
