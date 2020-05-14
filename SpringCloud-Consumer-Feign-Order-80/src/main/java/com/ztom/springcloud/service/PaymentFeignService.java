package com.ztom.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ztom.springcloud.entities.CommonResult;
import com.ztom.springcloud.entities.Payment;

@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE") // 註解後添加被調用微服務名
public interface PaymentFeignService {
	
	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
	
	@GetMapping(value = "/payment/feign/timeout")
	public String paymentFeignTimeout();
}
