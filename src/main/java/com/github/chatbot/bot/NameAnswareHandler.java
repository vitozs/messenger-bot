package com.github.chatbot.bot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.github.chatbot.util.RegexMatcher.regexMatchWithInput;

public class NameAnswareHandler implements Answare{
    private final String regex = "\\b(?:nome|chama)\\b";
    private final List<String> NAME = Arrays.asList(
            "Me chamo Vitor!",
            "Opa, meu nome é Vitor",
            "Vitor é o meu nome"
    );
    @Override
    public boolean hasPattern(String userInput){
        return regexMatchWithInput(regex, userInput);
    }

    @Override
    public String generateResponse() {
        Random rand = new Random();
        return NAME.get(rand.nextInt(3));
    }
}
