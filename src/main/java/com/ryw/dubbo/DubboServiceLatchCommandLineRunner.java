package com.ryw.dubbo;

import org.springframework.boot.CommandLineRunner;

public class DubboServiceLatchCommandLineRunner implements CommandLineRunner{

	private String domain = "com.ryw.services.management";
	
	
	@Override
	public void run(String... arg0) throws Exception {
		ShutdownLatch latch = new ShutdownLatch(getDomain());
		latch.await();
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}

}
