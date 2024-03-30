package com.github.chatbot.answares;

import com.github.chatbot.strategies.AnswareStrategy;

import java.util.Arrays;
import java.util.List;

public class AnswaresCollection {
    private List<AnswareStrategy> answareStrategies = Arrays.asList(
            new AgeAnswareHandler(),
            new GreetingAnswareHandler(),
            new RequestWeatherAnswareHandler(),
            new NameAnswareHandler(),
            new WeatherInformationAnswareHandler()
    );

    public List<AnswareStrategy> getAnswares(){
        return answareStrategies;
    }
}