package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoResilience4jBulkheadProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoResilience4jBulkheadProductServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.build();
	}
}
