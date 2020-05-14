package com.ztom.springcloud.service;

import com.ztom.springcloud.entities.Payment;

public interface PaymentService {
	public int create(Payment payment);

	public Payment getPaymentById(Long id);
}
