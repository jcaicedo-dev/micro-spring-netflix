package io.github.codingpath.parameter.model;

import io.github.codingpath.parameter.util.ParameterType;

public class Parameter {

	private String key;
	private ParameterType parameterType;
	private String value;
	
	public Parameter() {
		super();
	}

	public Parameter(String key, ParameterType parameterType, String value) {
		super();
		this.key = key;
		this.parameterType = parameterType;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ParameterType getParameterType() {
		return parameterType;
	}

	public void setParameterType(ParameterType parameterType) {
		this.parameterType = parameterType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
