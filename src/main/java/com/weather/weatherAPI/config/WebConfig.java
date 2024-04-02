package com.weather.weatherAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/weather/**")
                .allowedOrigins("*") // Replace with your allowed origin(s)
                .allowedMethods("GET") // Add allowed methods if needed (e.g., "POST", "PUT")
                .allowedHeaders("*") // Adjust allowed headers based on your requirements (consider security)
                .allowCredentials(false); // Set to true if credentials are passed (e.g., cookies, session tokens)
    }
}