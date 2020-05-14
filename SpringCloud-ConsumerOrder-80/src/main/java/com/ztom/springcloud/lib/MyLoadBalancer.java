package com.ztom.springcloud.lib;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

@Component
public class MyLoadBalancer implements LoadBalancer {

	private AtomicInteger atomicInteger = new AtomicInteger(0);

	public final int getAnIncrement() {
		int current;
		int next;
		
		do {
			current = this.atomicInteger.get();
			next = current >= 2147483647 ? 0 : current + 1;
		} while (!this.atomicInteger.compareAndSet(current, next)); 
		System.out.println("*****第几次访问,次数next: " + next);
		return next;
	}

	@Override
	public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
		int index = getAnIncrement() % serviceInstances.size();
		return serviceInstances.get(index);
	}

}
