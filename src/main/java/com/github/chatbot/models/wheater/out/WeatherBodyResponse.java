package com.github.chatbot.models.wheater.out;

import lombok.Data;

import java.util.List;

@Data
public class WeatherBodyResponse {
    private Long id;
    private String name;
    private String state;
    private String country;
    private WeatherDataResponse data;

}
