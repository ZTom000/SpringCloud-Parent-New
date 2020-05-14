package com.ztom.springcloud.service.impl;

import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ztom.springcloud.service.PaymentService;

import cn.hutool.core.util.IdUtil;

@Service
public class PaymentServiceImpl implements PaymentService{
	

	@Override
	public String paymentInfoOK(Integer id) {
		
		return "线程池： " + Thread.currentThread().getName() + " paymentInfoOK,id: " + id + "\t O(∩_∩)O哈哈";
	}

	@Override
	@HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") // 配置超时时间
	})
	public String paymentInfoTimeOut(Integer id) {
		int timeout = 5;
		int age = 10 / 0; 
//		try {
//			TimeUnit.SECONDS.sleep(timeout);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return "线程池： " + Thread.currentThread().getName() + " paymentInfoTimeOut,id: " + id + "\t O(∩_∩)O哈哈 耗时(秒)： " + timeout;
	}

	@Override
	public String paymentInfoTimeOutHandler(Integer id) {
		
		return "线程池： " + Thread.currentThread().getName() + " 系统繁忙或者运行报错,请稍后再试,id: " + id + "\t o(╥﹏╥)o" ;
	}
	
	@Override
	@HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 窗口期时间
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 启动熔断所需请求失败次数
	})
	public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
		if(id < 0) {
			throw new RuntimeException("******id 不能为负数");
		}
		String serialNumber = IdUtil.simpleUUID();
		return Thread.currentThread().getName() + "\t调用成功,流水号: " + serialNumber;
	}

	@Override
	public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id) {
		return "id不能为负数,请稍后再试,o(╥﹏╥)o~~ id: " + id;
	}

}
