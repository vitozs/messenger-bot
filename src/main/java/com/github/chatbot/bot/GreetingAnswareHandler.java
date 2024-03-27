package com.github.chatbot.bot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.chatbot.util.RegexMatcher.regexMatchWithInput;

public class GreetingAnswareHandler implements Answare{
    private final String regex = "\\b(?:oi|boa tarde|ola)\\b";
    private final List<String> GREETING = Arrays.asList(
            "Opa! Sou um bot do messager, me pergunta alguma coisa!",
            "Ola! Agradeco o contato! Fico a disponha de qualquer duvida",
            "Prazer! Estou disposto a responder suas duvidas referente a mim"
    );
    @Override
    public boolean hasPattern(String userInput){
        return regexMatchWithInput(regex, userInput);
    }

    @Override
    public String generateResponse() {
        Random rand = new Random();
        return GREETING.get(rand.nextInt(3));
    }
}
