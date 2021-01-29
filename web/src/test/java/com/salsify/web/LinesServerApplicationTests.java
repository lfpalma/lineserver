package com.salsify.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
class LinesServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationArguments args;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetLineIndex()
			throws Exception {

		this.mockMvc
				.perform(
						get("/lines/1"))
				.andExpect(
						status().isOk());
	}

	@Test
	void testInvalidLine()
			throws Exception {

		this.mockMvc
				.perform(
						get("/lines/100"))
				.andExpect(
						status().is(HttpStatus.PAYLOAD_TOO_LARGE.value()));
	}

	@Test
	void testArguments() {
		Assertions.assertNotNull(args);
	}
}