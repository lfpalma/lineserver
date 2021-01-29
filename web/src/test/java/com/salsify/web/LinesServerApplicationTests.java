package com.salsify.web;

import com.salsify.lineserver.LineReader;
import com.salsify.lineserver.file.FileReaderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(LinesServerApplicationTests.Configuration.class)
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
	void testArguments() {
		Assertions.assertNotNull(args);
	}

	@TestConfiguration
	public static class Configuration {
		@Bean
		@Primary
		public LineReader getTestLineReader() {
			return index -> "Hello World";
		}
	}
}