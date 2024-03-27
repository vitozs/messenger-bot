package com.github.chatbot.bot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.chatbot.util.RegexMatcher.regexMatchWithInput;

public class AgeAnswareHandler implements Answare{
    private final String regex = "\\b(?:anos|idade)\\b";
    private final List<String> AGE = Arrays.asList(
            "Eu tenho 18 anos",
            "Claro, eu tenho 18 anos",
            "Tenho 18 anos"
    );
    @Override
    public boolean hasPattern(String userInput){
        return regexMatchWithInput(regex, userInput);
    }

    @Override
    public String generateResponse() {
        Random rand = new Random();
        return AGE.get(rand.nextInt(3));
    }
}
