package com.weather.weatherAPI.adapter;

import com.weather.weatherAPI.external.ExternalWeatherApi;
import com.weather.weatherAPI.util.WeatherApi;
import com.weather.weatherAPI.util.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class WeatherApiAdapter implements WeatherApi {
    private ExternalWeatherApi externalWeatherApi; // Interface or class representing the external API

    public WeatherApiAdapter(ExternalWeatherApi externalWeatherApi) {
        this.externalWeatherApi = externalWeatherApi;
    }

    @Override
    public WeatherData getWeather(String city) {
        // Adapt the external API method to the common interface
        return externalWeatherApi.fetchWeather(city);
    }
}
