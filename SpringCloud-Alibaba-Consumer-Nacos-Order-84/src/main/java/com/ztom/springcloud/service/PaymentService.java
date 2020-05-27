package com.ztom.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ztom.springcloud.entities.CommonResult;
import com.ztom.springcloud.entities.Payment;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {
	@GetMapping(value = "/paymentSQL/{id}")
	public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
