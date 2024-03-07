package com.weather.weatherAPI.external;

import com.weather.weatherAPI.util.ForecastResponse;
import com.weather.weatherAPI.util.WeatherApi;
import com.weather.weatherAPI.util.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Profile("openWeatherMap")
public class OpenWeatherMapApi implements ExternalWeatherApi {

    private WebClient.Builder webClientBuilder;

    private final String apiUrl;
    private final String apiKey;
    public OpenWeatherMapApi(WebClient.Builder webClientBuilder,  String apiUrl,String apiKey) {
        this.webClientBuilder = webClientBuilder;
        this.apiUrl = apiUrl;
        this.apiKey=apiKey;
    }
    @Override

    public WeatherData fetchWeather(String city) {
        String lat="44.34";
        String lon="10.99";

        String url = apiUrl + "/2.5/weather?lat="+lat+"&"+lon+"=&appid="+apiKey; // Adjust URL based on the external API's requirements
        // Make HTTP request to the external API
        Mono<WeatherData> weatherDataMono = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherData.class);

        // Block and get the result (you can handle it asynchronously if your application is reactive)
        return weatherDataMono.block();
    }

    public ForecastResponse forecastWeather(String city) {
        String lat="44.34";
        String lon="10.99";

        String url = apiUrl + "/2.5/weather?lat="+lat+"&"+lon+"=&appid="+apiKey; // Adjust URL based on the external API's requirements
        // Make HTTP request to the external API
        Mono<ForecastResponse> weatherDataMono = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ForecastResponse.class);

        // Block and get the result (you can handle it asynchronously if your application is reactive)
        return weatherDataMono.block();
    }


}
