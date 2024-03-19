package com.github.chatbot.services;

import com.github.chatbot.models.wheater.out.WeatherBodyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
public class WeatherConsultingService {
    final String CITY_ID = System.getenv("CITY_ID");
    final String WEATHER_TOKEN = System.getenv("WEATHER_TOKEN");
    public WeatherBodyResponse consultWeatherByCityId(){
        WebClient client =  WebClient.builder()
                .baseUrl("http://apiadvisor.climatempo.com.br/api/v1/weather/locale/" + CITY_ID)
                .build();

        WeatherBodyResponse weather = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current")
                        .queryParam("token", WEATHER_TOKEN )
                        .build())
                .retrieve()
                .bodyToMono(WeatherBodyResponse.class)
                .block();
        return weather;
    }
}
