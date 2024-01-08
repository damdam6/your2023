package com.ssafy.imgMaker22.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("https://api.openai.com/v1/images/generations", "https://api.openai.com/v1/chat/completions", "http://localhost:5173")
				.allowedMethods("GET", "POST").allowCredentials(true); // 쿠키 인증 요청 허용
	}
	
	
	
	

}