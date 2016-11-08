package com.devstepsblog.greetings.controller;

import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greetings")
public class GreetingsController {

	@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Resource<String> defaultGreetings(){
		return new Resource<>("Hello World!!");
	}

	@RequestMapping(value="/{name}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Resource<String> sayHi(@PathVariable("name")String name){
		return new Resource<>("Hello "+name+"!!");
	}
	
}
