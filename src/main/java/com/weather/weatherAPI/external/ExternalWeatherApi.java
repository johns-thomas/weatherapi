package com.weather.weatherAPI.external;

import com.weather.weatherAPI.util.ForecastResponse;
import com.weather.weatherAPI.util.WeatherData;

public interface ExternalWeatherApi {
    WeatherData fetchWeather(String city);
    ForecastResponse forecastWeather(String city);
}
