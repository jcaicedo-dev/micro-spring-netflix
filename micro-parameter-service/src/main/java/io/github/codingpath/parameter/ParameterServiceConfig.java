package io.github.codingpath.parameter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ParameterServiceConfig {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ParameterServiceConfig.class, args);
	}
	
}
