package com.ztom.springcloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ztom.springcloud.entities.Payment;
import com.ztom.springcloud.service.PaymentHystrixService;

import lombok.extern.slf4j.Slf4j;

@RestController
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod") // 设置默认降级调用方法
@Slf4j
public class OrderHystirxController {

	@Resource
	private PaymentHystrixService paymentHystrixService;
	
	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String getPaymentInfoOK(@PathVariable("id") Integer id) {
		String result = this.paymentHystrixService.paymentInfoOK(id);
		return result;
	}

	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
//	@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//			 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//	})
	@HystrixCommand
	public String getPaymentInfoTimeOut(@PathVariable("id") Integer id) {
		String result = this.paymentHystrixService.paymentInfoTimeOut(id);
		return result;
	}
	
	public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
		return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
	}
	
	public String paymentGlobalFallbackMethod() { // 不能带参
		return "Global异常处理信息,请稍后再试,o(╥﹏╥)o";
	}
}
