package com.zhangs.springboot.config;

import com.zhangs.springboot.component.LoginHandlerInterceptor;
import com.zhangs.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZhangS
 * @create 2020-09-11-18:58
 */
@Configuration
public class myMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
		registry.addViewController("/main.html").setViewName("dashboard");
	}

	@Bean
	public LocaleResolver localeResolver(){
		return new MyLocaleResolver();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//添加过滤器，拦截所有请求（保证用户需要先登录），但排除登录页面的请求和登录请求
		//springboot已经做好了静态资源的映射，无需排除静态资源请求的拦截
		registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
				excludePathPatterns("/","/user/login");
	}
}
