package com.github.chatbot.services;

import com.github.chatbot.exceptions.CityOrDateInvalid;
import com.github.chatbot.models.wheater.in.CityCordinatesBody;
import com.github.chatbot.models.wheater.in.WeatherBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class WeatherConsultingService {
    @Value("${weather.token}")
    private String WEATHER_TOKEN = "e18840b3d35e4327d78997c36be4513a";
    public WeatherBody getWeatherConditions(String city, String date){

        CityCordinatesBody cityCordinates = getCordinatesOfCity(city);
        Long lon = cityCordinates.getLon();
        Long lat = cityCordinates.getLat();

        WebClient client =  WebClient.builder()
                .baseUrl("https://api.openweathermap.org/data/3.0/onecall/")
                .build();

        ResponseEntity<WeatherBody> weather = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/day_summary")
                        .queryParam("lon", lon)
                        .queryParam("lat", lat)
                        .queryParam("date", date)
                        .queryParam("units", "metric")
                        .queryParam("appid", WEATHER_TOKEN)
                        .build())
                .retrieve()
                .toEntity(WeatherBody.class)
                .block();
        return weather.getBody();

    }
    public CityCordinatesBody getCordinatesOfCity(String city){

        try {
            WebClient client =  WebClient.builder()
                    .baseUrl("http://api.openweathermap.org/geo/1.0/")
                    .build();

            ResponseEntity<List<CityCordinatesBody>> coordinates = client.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/direct")
                            .queryParam("q", city)
                            .queryParam("appid", WEATHER_TOKEN )
                            .build())
                    .retrieve()
                    .toEntityList(CityCordinatesBody.class)
                    .block();
            return coordinates.getBody().get(0);
        }catch (Exception e){
            throw new CityOrDateInvalid("Cidade inválida!");
        }

    }
}

