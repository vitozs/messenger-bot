package com.github.chatbot.strategies;

import java.io.IOException;

public interface AnswareStrategy {
    public boolean hasPattern(String expectedIntent);
    public String generateResponse(String userText) throws IOException;
}