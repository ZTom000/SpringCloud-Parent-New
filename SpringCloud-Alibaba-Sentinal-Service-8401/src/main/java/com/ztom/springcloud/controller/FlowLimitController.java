package com.ztom.springcloud.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FlowLimitController {

	@GetMapping("/testa")
	public String testA() {

		return "------testA";
	}

	@GetMapping("/testb")
	public String testB() {
		log.info(Thread.currentThread().getName() + "\t" + "...testB");
		return "------testB";
	}

	@GetMapping("/testd")
	public String testD() {
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		log.info("testD 测试RT");
		log.info("testD 测试异常比例");
		int age = 10 / 0;
		return "------testD";
	}

	@GetMapping("/teste")
	public String testE() {

		log.info("testE 测试异常数");
		int age = 10 / 0;
		return "------testE";
	}

	@GetMapping("/testHotKey")
	@SentinelResource(value = "testHotKey", blockHandler = "dealTestHotKey")
	public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
			@RequestParam(value = "p2", required = false) String p2) {
		// int age = 10 / 0;
		return "------testHotKey";
	}

	public String dealTestHotKey(String p1, String p2, BlockException exception) {
		return "------dealTestHotKey,o(╥﹏╥)o"; // sentinel系统默认的提示: Blocked by Sentinel (flow limiting)
	}
}
