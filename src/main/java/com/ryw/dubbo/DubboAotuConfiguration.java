package com.ryw.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//order 标注保证DubboServiceLatchCommandLineRunner在最后执行，已避免阻塞其他逻辑的执行
@Configuration
@Order
public class DubboAotuConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(DubboAotuConfiguration.class);
	
	@Value("${shutdown.latch.domain.name:com.ryw.services.management}")
	private String shutdownLatchDomainName;
	
	@Bean
	@ConditionalOnClass(name = "com.alibaba.dubbo.rpc.Exporter")
	public DubboServiceLatchCommandLineRunner configureDubboSerciceLatchCommandLineRunner() {
		logger.debug("DubboAutoConfiguration enbaled by adding DubboServiceLatchCommandLineRunning.");
		DubboServiceLatchCommandLineRunner runner = new DubboServiceLatchCommandLineRunner();
		runner.setDomain(shutdownLatchDomainName);
		return runner;
	}
	
}
