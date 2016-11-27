package io.github.codingpath.parameter.service;

import java.util.List;

import io.github.codingpath.parameter.model.Parameter;
import io.github.codingpath.parameter.util.ParameterType;

public interface ParameterService {

	void save(Parameter parameter, boolean replaceIfExists);
	
	Parameter findByKey(String key);

	void remove(String key);
	
	List<Parameter> findByType(ParameterType parameterType);
	
	List<Parameter> findAll();
	
}
