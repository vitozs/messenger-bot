package com.github.chatbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BotResponseBuilderService {

    @Autowired
    private BotConfigService botConfigService;
    private  String userInput;
    public String buildBotText(String userText) {
        String botText;
        this.userInput = userText;

        if(hasPattern("\\b(?:oi|boa tarde|ola)\\b")){
            botText = botConfigService.respondIfGreetings();
        }else if(hasPattern("\\b(?:nome|chama)\\b")){
            botText = botConfigService.respondIfName();
        }else if(hasPattern("\\b(?:anos|idade)\\b")){
            botText = botConfigService.respondIfAge();
        } else if(hasPattern("\\b(?:tempo)\\b")){
            botText = botConfigService.respondIfWeather();
        }else{
            botText = botConfigService.respondIfNoAlternative();
        }

        return botText;
    }
    private boolean hasPattern(String regex){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.userInput);
        return matcher.find();
    }

}
