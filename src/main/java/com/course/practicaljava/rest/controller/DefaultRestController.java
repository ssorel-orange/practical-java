/**
 * 
 */
package com.course.practicaljava.rest.controller;

import java.time.LocalTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steph
 *
 */
@RestController
@RequestMapping("/api")
public class DefaultRestController {
	
	private Logger log = LoggerFactory.getLogger(DefaultRestController.class);
	
	@GetMapping ("/welcome")
	public String welcome () {
		log.info(StringUtils.join("Hello ", "This is ", "SpringBoot " , "REST API"));
		return "Welcome to Spring boot";
	}

	
	@GetMapping("/time")
	public String time( ) {
		return LocalTime.now().toString();
	}
	@GetMapping (path ="/header-one")
	public String headerByAnnotation(@RequestHeader(name = "User-agent") String headerUserAgent, @RequestHeader(name= "Practical-java", required = false) String headerPracticalJava) {
		StringBuilder sb = new StringBuilder();
		sb.append("User-agent : " + headerUserAgent);
		sb.append(" || ");
		sb.append("Practical-java : " + headerPracticalJava);
		return sb.toString();
		
	}
	@GetMapping (path ="/header-two")
	public String headerByServlet(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("User-agent : " + request.getHeader("User-agent"));
		sb.append(" || ");
		sb.append("Practical-java : " + request.getHeader("Practical-java"));
		return sb.toString();
	}
	@GetMapping (path = "/header-three")
	public Map<String, String> headerByAll(@RequestHeader HttpHeaders httpHeaders) {
		return httpHeaders.toSingleValueMap();
	}
}
