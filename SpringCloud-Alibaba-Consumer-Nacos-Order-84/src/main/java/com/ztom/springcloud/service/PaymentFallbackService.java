package com.ztom.springcloud.service;

import org.springframework.stereotype.Component;

import com.ztom.springcloud.entities.CommonResult;
import com.ztom.springcloud.entities.Payment;

@Component
public class PaymentFallbackService implements PaymentService{
	@Override
	public CommonResult<Payment> paymentSQL(Long id) {
		return new CommonResult<>(444, "服务降级返回--PaymentFallbackService", new Payment(id,"errorSerial"));
	}
}
