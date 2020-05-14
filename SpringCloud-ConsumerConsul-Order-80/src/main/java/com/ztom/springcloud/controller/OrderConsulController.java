package com.ztom.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderConsulController {
	public static final String INVOKE_URL = "http://consul-provider-service";
	
	@Resource
	private RestTemplate restTemplate;
	
	@GetMapping(value = "/consumer/payment/consul")
	public String paymentInfo() {
		
		String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
		return result;
	}
}
