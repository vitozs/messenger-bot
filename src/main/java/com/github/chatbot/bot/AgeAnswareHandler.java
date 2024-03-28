package com.github.chatbot.bot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.github.chatbot.util.IntentMatcher.checkIntent;

public class AgeAnswareHandler implements AnswareStrategy {
    private final String intent = "\\b(?:anos|idade)\\b";
    private final List<String> AGE = Arrays.asList(
            "Eu tenho 18 anos",
            "Claro, eu tenho 18 anos",
            "Tenho 18 anos"
    );
    @Override
    public boolean hasPattern(String intentReturned){
        return checkIntent(intent, intentReturned);
    }

    @Override
    public String generateResponse() {
        Random rand = new Random();
        return AGE.get(rand.nextInt(3));
    }
}