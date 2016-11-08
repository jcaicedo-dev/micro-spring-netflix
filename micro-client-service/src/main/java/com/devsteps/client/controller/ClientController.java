package com.devsteps.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsteps.client.service.ClientService;

@RestController
@RequestMapping("client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Resource<String> sayHiUsingId(@PathVariable(name="id", required=true) int clientId){
		return new Resource<String>(clientService.sayHiUsingId(clientId));
	}
	
}
