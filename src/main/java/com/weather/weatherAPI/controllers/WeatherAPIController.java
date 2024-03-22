package com.weather.weatherAPI.controllers;

import com.weather.weatherAPI.adapter.WeatherApiAdapter;
import com.weather.weatherAPI.util.ForecastResponse;
import com.weather.weatherAPI.util.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherAPIController {

    @Autowired
    private WeatherApiAdapter weatherApiAdapter;
    @GetMapping("/weather/today")
    public WeatherData getCustomer(@RequestParam String city) {
        return weatherApiAdapter.getWeather(city);

    }
    @GetMapping("/weather/forecast")
    public ForecastResponse getForecast(@RequestParam String city){
        return weatherApiAdapter.forecastWeather(city);
    }
}
