package com.weather.weatherAPI.controllers;

import com.weather.weatherAPI.adapter.WeatherApiAdapter;
import com.weather.weatherAPI.util.ForecastResponse;
import com.weather.weatherAPI.util.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherAPIController {

    @Autowired
    private WeatherApiAdapter weatherApiAdapter;
    @GetMapping("/today")
    public WeatherData getCustomer(@RequestParam String city) {
        return weatherApiAdapter.getWeather(city);

    }
    @GetMapping("/forecast")
    public ForecastResponse getForecast(@RequestParam String city){
        return weatherApiAdapter.forecastWeather(city);
    }
}
