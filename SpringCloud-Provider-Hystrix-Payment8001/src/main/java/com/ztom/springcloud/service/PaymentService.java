package com.ztom.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
	
	public String paymentInfoOK(Integer id);
	
	public String paymentInfoTimeOut(Integer id);
	
	public String paymentInfoTimeOutHandler(Integer id);
	
	public String paymentCircuitBreaker(Integer id);
	
	public String paymentCircuitBreakerFallback(Integer id);
}
