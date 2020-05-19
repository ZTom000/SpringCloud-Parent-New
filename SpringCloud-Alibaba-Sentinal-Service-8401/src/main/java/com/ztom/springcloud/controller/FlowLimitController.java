package com.ztom.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
	
	@GetMapping("/testa")
	public String testA() {
		return "------testA";
	}
	
	@GetMapping("/testb")
	public String testB() {
		return "------testB";
	}
}
