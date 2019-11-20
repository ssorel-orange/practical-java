package com.course.practicaljava.rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class DefaultRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testWelcome() throws Exception {
		var endpoint = "/api/welcome";
		var requestBuilder = MockMvcRequestBuilders.get(endpoint);
		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(Matchers.equalToIgnoringCase("welcome to spring boot")));
		//fail("Not yet implemented");
	}

	@Test
	public void testTime() throws Exception {
		var endpoint = "/api/time";
		var requestBuilder = MockMvcRequestBuilders.get(endpoint);
		var mockResult = mockMvc.perform(requestBuilder).andReturn();
		var content = mockResult.getResponse().getContentAsString();
		
		var contentLocalTime = LocalTime.parse(content);
		var currentLocaleTime = LocalTime.now();
		var currentLocalTimeMinus40seconds = currentLocaleTime.minusSeconds(40);
		
		assertTrue(contentLocalTime.isAfter(currentLocalTimeMinus40seconds) && contentLocalTime.isBefore(currentLocaleTime));
		//fail("Not yet implemented");
	}

	@Test
	public void testHeaderByAnnotation() throws Exception {
		var endpoint = "/api/header-one";
		
		var headerOne = "MockMvc";
		var headerTwo = "MyJava";
		
		var httpHeaders = new HttpHeaders();
	    httpHeaders.add("user-agent", headerOne);
	    httpHeaders.add("Practical-java",headerTwo);
	    
	    
	    var requestBuilder = MockMvcRequestBuilders.get(endpoint).headers(httpHeaders);
	    mockMvc.perform(requestBuilder).andExpect(content().string(containsString(headerOne))).andExpect(content().string(containsString(headerTwo)));
		
		
		//fail("Not yet implemented");
	}

	@Test
	public void testHeaderByServlet() {
		fail("Not yet implemented");
	}

	@Test
	public void testHeaderByAll() {
		fail("Not yet implemented");
	}

}
