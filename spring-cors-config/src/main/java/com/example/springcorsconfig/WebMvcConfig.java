package com.example.springcorsconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/cors")
                .allowedOrigins("http://localhost:5500")
                .allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name());
    }
}
