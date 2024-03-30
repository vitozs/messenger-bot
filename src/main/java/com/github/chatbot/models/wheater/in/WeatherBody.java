package com.github.chatbot.models.wheater.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class WeatherBody {
    private TemperatureBody temperature;
    private WindBody wind;

    public Double getMaxTemperature(){
        return temperature.getMax();
    }

    public Double getMinTemperature(){
        return temperature.getMax();
    }

    public Double getWindSpeed(){
        return wind.getMax().getSpeed();
    }
}
