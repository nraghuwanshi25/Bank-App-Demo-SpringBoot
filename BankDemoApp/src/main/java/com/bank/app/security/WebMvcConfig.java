package com.bank.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
public class WebMvcConfig {
	@Autowired
	AppInterceptor appInterceptor;
//	
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(null);
//    }
}