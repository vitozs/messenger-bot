package com.github.chatbot.services;

import com.github.chatbot.bot.Answare;
import com.github.chatbot.bot.AnswaresCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BotResponseBuilderService {

    AnswaresCollection answaresCollection = new AnswaresCollection();
    public String buildBotText(String userText) {
        String botText = "Não entendi!";

        for(Answare answare : answaresCollection.getAnswares()){
            if(answare.hasPattern(userText)){
                botText = answare.generateResponse();
            }
        }

        return botText;
    }

}
