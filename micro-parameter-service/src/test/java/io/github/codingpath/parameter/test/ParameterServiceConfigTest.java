package io.github.codingpath.parameter.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.codingpath.parameter.ParameterServiceConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		ParameterServiceConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
				"eureka.client.enabled=false", "spring.cloud.config.discovery.enabled=false", "spring.cloud.config.enabled=false" })
public class ParameterServiceConfigTest {

	@Test
	public void context_loads(){
		
	}
	
}
