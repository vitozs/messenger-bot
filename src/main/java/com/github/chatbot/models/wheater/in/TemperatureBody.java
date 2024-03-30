package com.github.chatbot.models.wheater.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class TemperatureBody {
    private double min;
    private double max;
}
