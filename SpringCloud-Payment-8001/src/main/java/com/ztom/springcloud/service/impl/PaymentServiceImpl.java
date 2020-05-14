package com.ztom.springcloud.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ztom.springcloud.dao.PaymentDao;
import com.ztom.springcloud.entities.Payment;
import com.ztom.springcloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private PaymentDao paymentDao;

	@Override
	public int create(Payment payment) {
		return this.paymentDao.create(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {
		return this.paymentDao.getPaymentById(id);
	}

}
