package com.github.chatbot.bot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.github.chatbot.util.IntentMatcher.checkIntent;

public class NameAnswareHandler implements AnswareStrategy {
    private final String intent = "name";
    private final List<String> NAME = Arrays.asList(
            "Me chamo Vitor!",
            "Opa, meu nome é Vitor",
            "Vitor é o meu nome"
    );
    @Override
    public boolean hasPattern(String intentReturned){
        return checkIntent(intent, intentReturned);
    }

    @Override
    public String generateResponse() {
        Random rand = new Random();
        return NAME.get(rand.nextInt(3));
    }
}