package com.pickme.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PickMeDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickMeDiscoveryServerApplication.class, args);
	}

}
