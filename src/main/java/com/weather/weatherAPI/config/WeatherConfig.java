package com.weather.weatherAPI.config;


import com.weather.weatherAPI.external.OpenWeatherMapApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WeatherConfig {
    @Bean
    public OpenWeatherMapApi openWeatherMapApi(WebClient.Builder webClient, @Value("${openweathermap.api.key}") String apiKey, @Value("${openweathermap.api.url}")String apiUrl) {
        return new OpenWeatherMapApi(webClient,apiUrl, apiKey);
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
