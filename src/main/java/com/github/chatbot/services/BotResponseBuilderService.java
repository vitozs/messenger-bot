package com.github.chatbot.services;

import com.github.chatbot.models.wheater.out.WeatherBodyResponse;
import com.github.chatbot.models.wheater.out.WeatherDataResponse;
import com.github.chatbot.util.BotResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BotResponseBuilderService {
    @Autowired
    WeatherConsultingService weatherConsultingService;
    private  String userInput;
    public String buildBotText(String userText) {
        String botText;
        this.userInput = userText;

        if(hasPattern("\\b(?:oi|boa tarde|ola)\\b")){
            botText = BotResponses.responderGreetings();
        }else if(hasPattern("\\b(?:nome|chama)\\b")){
            botText = BotResponses.responderNome();
        }else if(hasPattern("\\b(?:anos|idade)\\b")){
            botText = BotResponses.responderIdade();
        }
        else if(hasPattern("\\b(?:tempo)\\b")){
            botText = treatWeatherResponse();
        }else{
            botText = BotResponses.responderNENHUMA_ALTERNATIVA();
        }

        return botText;
    }

    private boolean hasPattern(String regex){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.userInput);
        return matcher.find();
    }

    private String treatWeatherResponse(){
        WeatherBodyResponse body = weatherConsultingService.consultWeatherByCityId();
        WeatherDataResponse weatherData = body.getData();

        return "Cidade: " + body.getName() + "\n" +
                "Condicao: " + weatherData.getCondition();
    }
}
