package com.example.login;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.login.intercepter.AutoLoginIntercepter;

@Configuration
public class ServerConfigure implements WebMvcConfigurer{
	private static final List<String> URL_PATTERNS = Arrays.asList("/login/test");  //인터셉터가 동작 해야 될 요청 주소 mapping 목록
	
	//인터셉터 주소 세팅
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AutoLoginIntercepter()).addPathPatterns(URL_PATTERNS);
	}
}