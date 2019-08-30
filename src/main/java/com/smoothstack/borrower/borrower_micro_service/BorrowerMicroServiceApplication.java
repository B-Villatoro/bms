package com.smoothstack.borrower.borrower_micro_service;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BorrowerMicroServiceApplication {
	
	
	
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // It will set UTC timezone
    }

	public static void main(String[] args) {
		SpringApplication.run(BorrowerMicroServiceApplication.class, args);
		
	}

}
