package com.github.chatbot.models.wheater.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherBodyResponse {
    private Main main;
    private Sys sys;
    private String name;

    public String getCountry(){
        return sys.getCountry();
    }

    public double getTemp(){
        return main.getTemp();
    }
}
