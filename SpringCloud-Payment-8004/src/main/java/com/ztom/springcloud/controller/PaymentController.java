package com.ztom.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController {
	
	@Value("${server.port}")
	private String serverPort;
	
	@RequestMapping(value="/payment/zk")
	public String paymentZk() {
		return "Spring Cloud with zookeeper: " + serverPort + "\t " + UUID.randomUUID().toString();
	}
}
