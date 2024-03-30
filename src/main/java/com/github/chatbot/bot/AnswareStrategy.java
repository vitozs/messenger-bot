package com.github.chatbot.bot;

public interface AnswareStrategy {
    public void setUserText(String userText);
    public boolean hasPattern(String expectedIntent);
    public String generateResponse(String userText);
}