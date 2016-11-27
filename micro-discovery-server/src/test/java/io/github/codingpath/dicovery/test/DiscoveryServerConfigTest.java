package io.github.codingpath.dicovery.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.codingpath.discovery.DiscoveryServerConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DiscoveryServerConfig.class})
public class DiscoveryServerConfigTest {

	@Test
	public void context_loads(){
		
	}
	
}
