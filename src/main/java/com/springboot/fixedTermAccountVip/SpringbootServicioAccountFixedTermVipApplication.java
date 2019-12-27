package com.springboot.fixedTermAccountVip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioAccountFixedTermVipApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioAccountFixedTermVipApplication.class, args);
	}

}
