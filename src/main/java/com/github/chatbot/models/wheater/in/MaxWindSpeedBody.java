package com.github.chatbot.models.wheater.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class MaxWindSpeedBody {
    private double speed;
}
