package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.example.demo.controller.SecuredController;

/**
 * Testing Controllers
 */
@WebMvcTest(SecuredController.class)
class SecuredControllerWebMvcIntegrationTest {

	@Autowired
	private MockMvc mvc;

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
		Throwable exception = assertThrows(NestedServletException.class, () -> {
			mvc.perform(get("/private/hello-method").contentType(MediaType.APPLICATION_JSON));
		}).getRootCause();
		assertEquals(exception.getClass(), NullPointerException.class);
	}

}
