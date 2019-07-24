package com.web.kakaobank.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableTransactionManagement
public class CustomResourceHandler implements WebMvcConfigurer {
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {  "classpath:/static/"};

	  
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry	
		    .addResourceHandler("/**")
		    .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
		    .setCachePeriod(3600)
		    .resourceChain(true)
		    .addResolver(new PathResourceResolver());
	}
}
