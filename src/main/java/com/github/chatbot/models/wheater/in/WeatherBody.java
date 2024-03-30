package com.github.chatbot.models.wheater.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class WeatherBody {
    private TemperatureBody temperature;
    private WindBody wind;

    public String getMaxTemperature(){
        return formatTemperature(temperature.getMax());
    }

    public String getMinTemperature(){
        return formatTemperature(temperature.getMin());
    }

    public Double getWindSpeed(){
        return wind.getMax().getSpeed();
    }

    private String formatTemperature(double temperature){
        DecimalFormat formatoDecimal = new DecimalFormat("#00");
        String result = formatoDecimal.format(temperature);
        return result + "°C";
    }
}
