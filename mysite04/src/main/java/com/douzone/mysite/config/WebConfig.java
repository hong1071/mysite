package com.douzone.mysite.config;

import java.security.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;
import com.douzone.mysite.interceptor.SiteInterceptor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.mysite.controller", "com.douzone.mysite.exception"})
@Import({MessageConfig.class, FileUploadConfig.class, SecurityConfig.class, MvcConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter{
	
	//Interceptor
	@Bean
	public HandlerInterceptor siteInterceptor() {
		
		return new SiteInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(siteInterceptor())
			.addPathPatterns("/**");

	}
}
