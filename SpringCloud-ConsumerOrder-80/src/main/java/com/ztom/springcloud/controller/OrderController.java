package com.ztom.springcloud.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ztom.springcloud.entities.CommonResult;
import com.ztom.springcloud.entities.Payment;
import com.ztom.springcloud.lib.LoadBalancer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";
	
	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	private LoadBalancer loadBalancer;

	@Resource
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/consumer/create")
	public CommonResult<Payment> create(Payment payment){
		return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
	}
	@GetMapping("/consumer/postforentity/create")
	public CommonResult<Payment> createPostForEntity(Payment payment){
		ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
		if(entity.getStatusCode().is2xxSuccessful()) {
			return entity.getBody();
		}else {
			return new CommonResult<>(444, "操作失败");
		}
	}
	
	// getForObject 仅返回对象,不包含其他http信息
	@GetMapping("/consumer/get/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
		return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
	}
	
	// getForEntity 返回完整http对象，包含完整http信息
	@GetMapping("/consumer/getforentity/get/")
	public CommonResult<Payment> getPaymentForEntity(@PathVariable("id") Long id){
		ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
		if(entity.getStatusCode().is2xxSuccessful()) {
			return entity.getBody();
		}else {
			return new CommonResult<>(444, "操作失败");
		}
	}
	
	@GetMapping(value = "/consumer/payment/lb")
	public String getPaymentLB() {
		List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
		
		if(instances == null || instances.size() <= 0) {
			return null;
		}
		
		ServiceInstance serviceInstance = loadBalancer.instances(instances);
		URI uri = serviceInstance.getUri();
		
		return restTemplate.getForObject(uri + "/payment/lb", String.class);
	}
	
	@GetMapping(value = "/consumer/payment/zipkin")
	public String paymentZipkin() {
		String result = restTemplate.getForObject("http://localhost:8001/payment/zipkin", String.class);
		return result;
	}
}
