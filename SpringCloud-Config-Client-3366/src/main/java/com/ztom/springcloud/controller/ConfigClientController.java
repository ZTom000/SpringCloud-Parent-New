package com.ztom.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
	
	// 读取application.yml端口信息
	@Value("${server.port}")
	private String serverPort;
	
	// 读取配置中心配置文件信息
	@Value("${config.info}")
	private String configInfo;
	
	@GetMapping("/configInfo")
	public String configInfo() {
		return "serverPort: " + serverPort + "\t\n\n configInfo: " + configInfo;
	}
}
