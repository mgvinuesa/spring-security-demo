package com.example.demo;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecuredMethodSpringBootIntegrationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@WithMockUser(value = "Mocked1")
	@Test
	public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		mvc.perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@WithMockUser(value = "Mocked2", roles = "STAFF")
	@Test
	public void givenAuthRequestOnPrivateService_shouldFailWith403() throws Exception {
		mvc.perform(get("/private/hello").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}

	@WithMockUser(value = "Mocked1")
	@Test
	public void givenAuthRequestOnPrivateServiceWithBeanInjection_ShouldFailWithNullPointer() throws Exception {
		mvc.perform(get("/private/hello-method").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
