package com.github.chatbot.services;

import com.github.chatbot.models.dialogFlow.out.Fulillment;
import com.github.chatbot.models.wheater.out.WeatherBodyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class WeatherConsultingService {
    private final String WEATHER_TOKEN = System.getenv("WEATHER_TOKEN");
    public WeatherBodyResponse consultWeatherByCity(String city){
        WebClient client =  WebClient.builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .build();

        ResponseEntity<WeatherBodyResponse> weather = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", WEATHER_TOKEN )
                        .build())
                .retrieve()
                .toEntity(WeatherBodyResponse.class)
                .block();
        return weather.getBody();
    }

}
