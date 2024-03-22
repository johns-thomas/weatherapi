package com.weather.weatherAPI.external;

import com.weather.weatherAPI.util.ForecastResponse;
import com.weather.weatherAPI.util.WeatherApi;
import com.weather.weatherAPI.util.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@Profile("openWeatherMap")
public class OpenWeatherMapApi implements ExternalWeatherApi {

    private WebClient.Builder webClientBuilder;

    private final String apiUrl;
    private final String apiKey;
    @Value("${geolocation.api.url}")
    private String geolocationApiUrl;
    public OpenWeatherMapApi(WebClient.Builder webClientBuilder, String apiUrl, String apiKey) {
        this.webClientBuilder = webClientBuilder;
        this.apiUrl = apiUrl;
        this.apiKey=apiKey;

    }
    @Override

    public WeatherData fetchWeather(String city) {

        GeolocationResponse res =getGeolocationCodes(city);
        String lat=Double.toString(res.getLatitude());
        String lon=Double.toString(res.getLongitude());

        String url = apiUrl + "/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+apiKey+"&units=metric"; // Adjust URL based on the external API's requirements
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
        GeolocationResponse res =getGeolocationCodes(city);
        String lat=Double.toString(res.getLatitude());
        String lon=Double.toString(res.getLongitude());

        String url = apiUrl + "/2.5/forecast?lat="+lat+"&lon="+lon+"&appid="+apiKey+"&units=metric"; // Adjust URL based on the external API's requirements
        // Make HTTP request to the external API
        Mono<ForecastResponse> weatherDataMono = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ForecastResponse.class);

        // Block and get the result (you can handle it asynchronously if your application is reactive)
        return weatherDataMono.block();
    }

    private GeolocationResponse getGeolocationCodes(String city){

        GeolocationRequest req=new GeolocationRequest("coordinates",city);

        Mono<GeolocationResponse> res= webClientBuilder.build().post()
                .uri(geolocationApiUrl).accept(MediaType.APPLICATION_JSON) // Set the endpoint URI
                .bodyValue(req) // Set the request body
                .retrieve()
                .bodyToMono(GeolocationResponse.class);

        return res.block();

    }

}
