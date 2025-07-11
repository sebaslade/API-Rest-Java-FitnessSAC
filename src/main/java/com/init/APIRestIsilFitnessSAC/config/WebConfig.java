package com.init.APIRestIsilFitnessSAC.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("https://fitnesssac-qip31p5z2-sebaslades-projects.vercel.app")
            .allowedMethods("*")
            .allowedHeaders("*");
    }
}
