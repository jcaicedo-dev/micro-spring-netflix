package io.github.codingpath.config.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.codingpath.config.ConfigurationServerConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		ConfigurationServerConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
				"eureka.client.enabled=false" })
public class ConfigurationServerConfigTest {

	@Test
	public void context_loads() {

	}

}
