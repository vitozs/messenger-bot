package com.github.chatbot.exceptions;

public class CityOrDateInvalid extends RuntimeException{
    public CityOrDateInvalid(String message){
        super(message);
    }
}
