package com.github.chatbot.models.wheater.out;

import lombok.Data;

@Data
public class WeatherDataResponse {
    private int temperature;
    private String wind_direction;
    private int wind_velocity;
    private int humidity;
    private String condition;
    private long pressure;
    private String icon;
    private int sensation;
    private String date;
}
