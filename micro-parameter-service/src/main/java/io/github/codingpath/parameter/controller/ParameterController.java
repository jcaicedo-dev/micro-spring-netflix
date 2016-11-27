package io.github.codingpath.parameter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.codingpath.parameter.model.Parameter;
import io.github.codingpath.parameter.service.ParameterService;

@RestController
@RequestMapping("parameter")
public class ParameterController {

	@Autowired
	private ParameterService parameterService;

	@Description("Returns the list of all the parameters in JSON format")
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Parameter>> getAll() {
		return new ResponseEntity<List<Parameter>>(parameterService.findAll(), HttpStatus.OK);
	}

	@Description("Returns a single parameter filtered by the key in JSON format")
	@RequestMapping(method = RequestMethod.GET, value = "/{key}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<Parameter> getByKey(@PathVariable("key") String key) {
		Parameter parameter = parameterService.findByKey(key);
		if (parameter == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Parameter>(parameter, HttpStatus.OK);
	}

	@Description("Creates a parameter or replace an existing one")
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<String> create(@RequestBody @Valid Parameter parameter,
			@RequestParam(required = false, defaultValue = "true") boolean replaceIfExists) {
		parameterService.save(parameter, replaceIfExists);
		return new ResponseEntity<String>("Parameter saved successfully", HttpStatus.CREATED);
	}
	
	@Description("Deletes a parameter")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{key}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<String> create(@PathVariable("key") String key) {
		parameterService.remove(key);
		return new ResponseEntity<String>("Parameter deleted successfully", HttpStatus.OK);
	}

}
