package io.github.codingpath.parameter.test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.github.codingpath.parameter.ParameterServiceConfig;
import io.github.codingpath.parameter.model.Parameter;
import io.github.codingpath.parameter.service.ParameterService;
import io.github.codingpath.parameter.util.ParameterType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ParameterServiceConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"eureka.client.enabled=false", "spring.cloud.config.discovery.enabled=false",
		"spring.cloud.config.enabled=false" })
@AutoConfigureMockMvc
public class ParameterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParameterService parameterService;

	@Test
	public void find_all() throws Exception {

		String key = "key1";
		String key2 = "key2";

		when(parameterService.findAll()).thenReturn(Arrays.asList(new Parameter(key, ParameterType.TEST, "value"),
				new Parameter(key2, ParameterType.TEST2, "value2")));

		mockMvc.perform(get("/parameter/")).andExpect(status().isOk()).andExpect(content()
				.json("[{key:key1, parameterType:TEST, value:value}, {key:key2, parameterType:TEST2, value:value2}]"));

		verify(parameterService, times(1)).findAll();
		verifyNoMoreInteractions(parameterService);

	}

	@Test
	public void find_by_id() throws Exception {

		String key = "key1";

		when(parameterService.findByKey(key)).thenReturn(new Parameter(key, ParameterType.TEST, "value"));

		mockMvc.perform(get("/parameter/key1")).andExpect(status().isOk())
				.andExpect(content().json("{key:key1, parameterType:TEST, value:value}"));

		verify(parameterService, times(1)).findByKey(key);
		verifyNoMoreInteractions(parameterService);

	}

	@Test
	public void not_found_by_id() throws Exception {

		String key = "key4";

		when(parameterService.findByKey(key)).thenReturn(null);

		mockMvc.perform(get("/parameter/key4")).andExpect(status().isNotFound());

		verify(parameterService, times(1)).findByKey(key);
		verifyNoMoreInteractions(parameterService);

	}

	@Test
	public void remove_by_id() throws Exception {

		String key = "key4";

		mockMvc.perform(delete("/parameter/key4")).andExpect(status().isOk());

		verify(parameterService, times(1)).remove(key);
		verifyNoMoreInteractions(parameterService);

	}

	@Test
	public void creates_no_replace() throws Exception {

		mockMvc.perform(post("/parameter/").content("{\"key\":\"key1\",\"parameterType\":\"TEST\",\"value\":\"value\"}")
				.contentType(MediaType.APPLICATION_JSON).param("replaceIfExists", "false"))
				.andExpect(status().isCreated());

		verify(parameterService, times(1)).save(any(), eq(false));
		verifyNoMoreInteractions(parameterService);

	}

}
