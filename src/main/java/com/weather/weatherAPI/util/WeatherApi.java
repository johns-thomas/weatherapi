package com.weather.weatherAPI.util;

public interface WeatherApi {
    WeatherData getWeather(String city);
    ForecastResponse forecastWeather(String city);
}
