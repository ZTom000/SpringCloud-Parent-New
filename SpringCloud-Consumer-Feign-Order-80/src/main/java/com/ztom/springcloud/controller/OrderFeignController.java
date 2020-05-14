package com.ztom.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ztom.springcloud.entities.CommonResult;
import com.ztom.springcloud.entities.Payment;
import com.ztom.springcloud.service.PaymentFeignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderFeignController {
	
	@Resource
	private PaymentFeignService paymentFeignService;
	
	@GetMapping(value = "/consumer/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
		return paymentFeignService.getPaymentById(id);
	}
	
	@GetMapping(value = "/consumer/payment/feign/timeout")
	public String paymentFeignTimeout() {
		
		// OpenFeign 自带Ribbon,客户端默认等待1秒钟
		return paymentFeignService.paymentFeignTimeout();
	}
}
