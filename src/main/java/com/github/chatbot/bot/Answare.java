package com.github.chatbot.bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Answare {
    public boolean hasPattern(String userInput);
    public String generateResponse();
}
