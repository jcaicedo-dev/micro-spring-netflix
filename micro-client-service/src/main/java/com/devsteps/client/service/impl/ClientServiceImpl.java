package com.devsteps.client.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsteps.client.greetings.GreetService;
import com.devsteps.client.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private GreetService greetService;

	private Map<Integer, String> clients = new HashMap<>();
	
	public ClientServiceImpl() {
		for (int i = 1; i <= 5; i++) {
			clients.put(i, "Client "+i);
		}
	}
	
	@Override
	public String getNameById(int id) {
		return clients.get(id);
	}

	@Override
	public String sayHiUsingId(int id) {
		String name = getNameById(id);
		return greetService.sayHi(name);
	}

}
