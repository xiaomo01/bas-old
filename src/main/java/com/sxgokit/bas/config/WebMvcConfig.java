package com.sxgokit.bas.config;

import com.sxgokit.bas.base.TokenComponent;
import com.sxgokit.bas.base.WebServiceAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author:Dukang
 * @createDate:2018年9月10日
 * @desc:此类中申明拦截器、过滤器等
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private TokenComponent tokenComponent;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 对webservice全路径进行拦截验证token
		 * 放行路径：login,/static/**
		 */
		registry.addInterceptor(new WebServiceAuthInterceptor(tokenComponent))
				//拦截所有请求
				.addPathPatterns("/**")
				.excludePathPatterns("/system/login/")
				// 静态资源
				.excludePathPatterns("/static/**")
				// 开放swagger-ui相关资源
                .excludePathPatterns("/doc.html")
				.excludePathPatterns("/swagger-resources/**", "/webjars/**",
						"/v2/**", "/swagger-ui.html/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
