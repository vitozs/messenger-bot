package com.github.chatbot.bot;

import java.util.Arrays;
import java.util.List;

public class AnswaresCollection {
    private List<AnswareStrategy> answareStrategies = Arrays.asList(
            new AgeAnswareHandler(),
            new GreetingAnswareHandler(),
            new WeatherAnswareHandler(),
            new NameAnswareHandler()
    );

    public List<AnswareStrategy> getAnswares(){
        return answareStrategies;
    }
}