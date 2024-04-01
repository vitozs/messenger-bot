package com.github.chatbot.answares;

import com.github.chatbot.strategies.AnswareStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.github.chatbot.util.IntentMatcher.checkIntent;

public class GreetingAnswareHandler implements AnswareStrategy {
    private final String intent = "greetings";
    private final List<String> GREETING = Arrays.asList(
            "Opa! Sou um bot do messager, me pergunta alguma coisa!",
            "Ola! Agradeco o contato! Fico a disponha de qualquer duvida",
            "Prazer! Estou disposto a responder suas duvidas referente a mim"
    );
    @Override
    public boolean hasPattern(String intentReturned){
        return checkIntent(intent, intentReturned);
    }
    @Override
    public String generateResponse(String userText) {
        Random rand = new Random();
        return GREETING.get(rand.nextInt(3));
    }
}