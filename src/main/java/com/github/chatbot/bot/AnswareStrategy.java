package com.github.chatbot.bot;

public interface AnswareStrategy {
    public boolean hasPattern(String expectedIntent);
    public String generateResponse();
}