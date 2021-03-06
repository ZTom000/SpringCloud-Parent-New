package com.ztom.springcloud.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ztom.springcloud.entities.CommonResult;
import com.ztom.springcloud.entities.Payment;

@RestController
public class PaymentController {
	
	@Value("${server.port}")
	private String serverPort;
	
	public static HashMap<Long, Payment> hashMap = new HashMap<Long, Payment>();
	
	static {
		hashMap.put(1L,new Payment(1L,"46dasdsa46sd867"));
		hashMap.put(2L,new Payment(2L,"7897sadasd4897s"));
		hashMap.put(3L,new Payment(3L,"7498sadfd44wee4"));
	}
	
	@GetMapping(value = "/paymentSQL/{id}")
	public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
		Payment payment = hashMap.get(id);
		CommonResult<Payment> result = new CommonResult(200, "from HashMap, server port: " + serverPort, payment);
		return result;
	}
}
