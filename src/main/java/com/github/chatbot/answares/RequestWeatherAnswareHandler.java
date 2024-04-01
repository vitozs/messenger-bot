package com.github.chatbot.answares;

import com.github.chatbot.strategies.AnswareStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.github.chatbot.util.IntentMatcher.checkIntent;


public class RequestWeatherAnswareHandler implements AnswareStrategy {
    private final String intent = "requestWeather";
    private final List<String> WEATHER = Arrays.asList(
            "Claro, me passa o nome da cidade e a data!",
            "Preciso do local e o dia",
            "Me mande o local e o dia para verificar a previsão do tempo"
    );
    @Override
    public boolean hasPattern(String intentReturned) {
        return checkIntent(intent, intentReturned);
    }
    @Override
    public String generateResponse(String userText) {
        Random rand = new Random();
        return WEATHER.get(rand.nextInt(3));
    }
}