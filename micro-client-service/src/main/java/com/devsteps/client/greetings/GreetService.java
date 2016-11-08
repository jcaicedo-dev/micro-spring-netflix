package com.devsteps.client.greetings;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="micro-greetings-service")
public interface GreetService {

	@RequestMapping(value="greetings/", method=RequestMethod.GET)
	String defaultGreetings();

	@RequestMapping(value="greetings/{name}", method=RequestMethod.GET)
	String sayHi(@PathVariable("name") String name);
	
}
