package com.pickme.rides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PickMeRidesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickMeRidesApplication.class, args);
	}

}
