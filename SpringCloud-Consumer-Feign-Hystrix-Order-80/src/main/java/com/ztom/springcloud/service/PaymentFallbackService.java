package com.ztom.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {

	@Override
	public String paymentInfoOK(Integer id) {
	
		return "-----PaymentFallbackService fall back paymentInfoOK, o(╥﹏╥)o";
	}

	@Override
	public String paymentInfoTimeOut(Integer id) {
	
		return "-----PaymentFallbackService fall back paymentInfoTimeOut, o(╥﹏╥)o";
	}

}
