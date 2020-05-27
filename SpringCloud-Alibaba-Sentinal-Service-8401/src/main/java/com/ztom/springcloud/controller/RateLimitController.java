package com.ztom.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ztom.springcloud.entities.CommonResult;
import com.ztom.springcloud.entities.Payment;
import com.ztom.springcloud.handler.CustomerBlockHandler;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RateLimitController {

	@GetMapping("/byResource")
	@SentinelResource(value = "byResource", blockHandler = "handleException") // value 需要与sentinel的资源名相同才生效
	public CommonResult byResource() {
		return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
	}

	public CommonResult handleException(BlockException exception) {
		return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
	}

	@GetMapping("/rateLimit/byUrl")
	@SentinelResource(value = "byUrl")
	public CommonResult byUrl() {
		return new CommonResult(200, "按url限流测试OK", new Payment(2020L, "serial002"));
	}

	@GetMapping("/rateLimit/customerblockhandler")
	@SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
	public CommonResult customerBlockHandler() {
		return new CommonResult(200, "按客户自定义", new Payment(2020L, "serial002"));
	}
}
