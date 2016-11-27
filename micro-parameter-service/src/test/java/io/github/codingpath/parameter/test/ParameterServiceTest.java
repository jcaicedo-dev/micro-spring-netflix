package io.github.codingpath.parameter.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.codingpath.parameter.ParameterServiceConfig;
import io.github.codingpath.parameter.model.Parameter;
import io.github.codingpath.parameter.service.ParameterService;
import io.github.codingpath.parameter.util.ParameterType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParameterServiceConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"eureka.client.enabled=false", "spring.cloud.config.discovery.enabled=false",
		"spring.cloud.config.enabled=false" })
public class ParameterServiceTest {

	@Autowired
	private ParameterService parameterService;

	@Test
	public void find_all_total() {
		int total = parameterService.findAll().size();
		Assert.assertEquals(3, total);
	}

	@Test
	public void find_by_key() {
		Parameter parameter1 = parameterService.findByKey("key1");
		Assert.assertNotNull(parameter1);
		Assert.assertEquals("key1", parameter1.getKey());
		Assert.assertEquals("value1", parameter1.getValue());
		Assert.assertEquals(ParameterType.TEST, parameter1.getParameterType());

		Parameter parameter2 = parameterService.findByKey("key2");
		Assert.assertNotNull(parameter2);
		Assert.assertEquals("key2", parameter2.getKey());
		Assert.assertEquals("value2", parameter2.getValue());
		Assert.assertEquals(ParameterType.TEST2, parameter2.getParameterType());
	}

	@Test
	public void not_found_by_key() {
		Parameter parameter1 = parameterService.findByKey("key4");
		Assert.assertNull(parameter1);
	}

	@Test
	public void find_by_type() {
		List<Parameter> params = parameterService.findByType(ParameterType.TEST);
		Assert.assertNotNull(params);
		int total = params.size();
		Assert.assertEquals(2, total);
		Assert.assertEquals("key1", params.get(0).getKey());
		Assert.assertEquals("key3", params.get(1).getKey());

		params = parameterService.findByType(ParameterType.TEST2);
		Assert.assertNotNull(params);
		total = params.size();
		Assert.assertEquals(1, total);
		Assert.assertEquals("key2", params.get(0).getKey());
	}

	@Test
	public void save_successful_no_replace() {
		parameterService.save(new Parameter("key4", ParameterType.TEST, "value4"), false);
		int total = parameterService.findAll().size();
		Assert.assertEquals(4, total);

		Parameter parameter1 = parameterService.findByKey("key4");
		Assert.assertNotNull(parameter1);
		Assert.assertEquals("key4", parameter1.getKey());
		Assert.assertEquals("value4", parameter1.getValue());
		Assert.assertEquals(ParameterType.TEST, parameter1.getParameterType());

		parameterService.remove("key4");
		total = parameterService.findAll().size();
		Assert.assertEquals(3, total);
	}

	@Test
	public void save_successful_replace() {
		parameterService.save(new Parameter("key4", ParameterType.TEST, "value4"), false);
		int total = parameterService.findAll().size();
		Assert.assertEquals(4, total);

		Parameter parameter1 = parameterService.findByKey("key4");
		Assert.assertNotNull(parameter1);
		Assert.assertEquals("key4", parameter1.getKey());
		Assert.assertEquals("value4", parameter1.getValue());
		Assert.assertEquals(ParameterType.TEST, parameter1.getParameterType());

		parameterService.save(new Parameter("key4", ParameterType.TEST2, "value5"), true);
		total = parameterService.findAll().size();
		Assert.assertEquals(4, total);

		parameter1 = parameterService.findByKey("key4");
		Assert.assertNotNull(parameter1);
		Assert.assertEquals("key4", parameter1.getKey());
		Assert.assertEquals("value5", parameter1.getValue());
		Assert.assertEquals(ParameterType.TEST2, parameter1.getParameterType());

		parameterService.remove("key4");
		total = parameterService.findAll().size();
		Assert.assertEquals(3, total);
	}

	@Test(expected = IllegalArgumentException.class)
	public void save_successful_replace_fail() {
		try {
			parameterService.save(new Parameter("key4", ParameterType.TEST, "value4"), false);
			int total = parameterService.findAll().size();
			Assert.assertEquals(4, total);

			parameterService.save(new Parameter("key4", ParameterType.TEST2, "value5"), false);
		} finally {
			parameterService.remove("key4");
			int total = parameterService.findAll().size();
			Assert.assertEquals(3, total);
		}

	}

}
