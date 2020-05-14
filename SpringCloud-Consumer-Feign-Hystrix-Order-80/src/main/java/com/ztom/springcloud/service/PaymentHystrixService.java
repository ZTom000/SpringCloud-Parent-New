package com.ztom.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-SERVICE", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
	
	@GetMapping("/payment/hystrix/ok/{id}")
	public String paymentInfoOK(@PathVariable("id") Integer id);
	
	@GetMapping("/payment/hystrix/timeout/{id}")
	public String paymentInfoTimeOut(@PathVariable("id") Integer id);

}
