//package com.smoothstack.borrower.borrower_micro_service.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.http.MediaType;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class ContentNegotiate implements WebMvcConfigurer {
//	
//	
//	
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		WebMvcConfigurer.super.configureContentNegotiation(configurer);
//		configurer.defaultContentType(MediaType.APPLICATION_JSON)
//		.mediaType("json", MediaType.APPLICATION_JSON)
//		.mediaType("xml", MediaType.APPLICATION_XML);
//	
//	}	
//}
