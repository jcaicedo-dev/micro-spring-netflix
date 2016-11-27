package io.github.codingpath.parameter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.codingpath.parameter.model.Parameter;
import io.github.codingpath.parameter.service.ParameterService;
import io.github.codingpath.parameter.util.ParameterType;

@Service
public class ParameterServiceImpl implements ParameterService {

	private static final Map<String, Parameter> PARAMETERS = new ConcurrentHashMap<>();
	
	{
		PARAMETERS.put("key1", new Parameter("key1", ParameterType.TEST, "value1"));
		PARAMETERS.put("key2", new Parameter("key2", ParameterType.TEST2, "value2"));
		PARAMETERS.put("key3", new Parameter("key3", ParameterType.TEST, "value3"));
	}

	@Override
	public void save(Parameter parameter, boolean replaceIfExists) {
		if (PARAMETERS.containsKey(parameter.getKey()) && !replaceIfExists) {
			throw new IllegalArgumentException("There is a parameter with the same identifier.");
		}

		PARAMETERS.put(parameter.getKey(), parameter);

	}

	@Override
	public Parameter findByKey(String key) {
		return PARAMETERS.get(key);
	}

	@Override
	public List<Parameter> findByType(ParameterType parameterType) {
		return PARAMETERS.values().stream().filter(param -> param.getParameterType() == parameterType)
				.collect(Collectors.toList());
	}

	@Override
	public List<Parameter> findAll() {
		return new ArrayList<>(PARAMETERS.values());
	}

	@Override
	public void remove(String key) {
		PARAMETERS.remove(key);
	}

}
