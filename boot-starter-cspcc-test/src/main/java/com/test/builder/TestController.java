package com.test.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

	static {
		log.info("INIT");
	}
	
	@Autowired
	private TestProps testProps;

	@GetMapping("test")
	public String test(String id) {
		log.info("{}", testProps);
		return testProps.toString();
	}

	@GetMapping("test1")
	public Long test1(String id) {
		return System.currentTimeMillis();
	}

	@GetMapping("test2")
	public Long test2(String id) {
		return System.currentTimeMillis();
	}

	@GetMapping("test3")
	public Long test3(String id) {
		return System.currentTimeMillis();
	}
}
