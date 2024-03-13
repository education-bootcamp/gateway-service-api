package com.devstack.lms.gatewayserviceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApiApplication.class, args);
	}

}
