package com.smoothstack.borrower.borrower_micro_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDiscoveryClient
public class BorrowerMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowerMicroServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer configurer() {
		return new WebMvcConfigurer() {

			@Override
			public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
				WebMvcConfigurer.super.configureContentNegotiation(configurer);
				configurer.defaultContentType(MediaType.APPLICATION_JSON).mediaType("json", MediaType.APPLICATION_JSON)
						.mediaType("xml", MediaType.APPLICATION_XML).mediaType("x-www-form-urlencoded", MediaType.APPLICATION_FORM_URLENCODED);
			}

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
