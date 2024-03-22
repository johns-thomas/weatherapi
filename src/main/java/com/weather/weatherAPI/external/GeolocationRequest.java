package com.weather.weatherAPI.external;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationRequest {
    private String to;
    private String name;

}
