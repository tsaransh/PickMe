package com.pickme.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class PickMeConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickMeConfigurationApplication.class, args);
	}

}
