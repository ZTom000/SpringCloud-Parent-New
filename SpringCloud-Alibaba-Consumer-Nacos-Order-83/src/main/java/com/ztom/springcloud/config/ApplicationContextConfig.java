package com.ztom.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
	
	@Bean
	@LoadBalanced // 配置负载均衡
	public RestTemplate getRestTempate() {
		return new RestTemplate();
	}
}
