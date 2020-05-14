package com.ztom.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class MySelfRule {
	@Bean
	public IRule myRule() {
		return new RandomRule(); // 定义Ribbon负载均衡规则为随机
	}
}
