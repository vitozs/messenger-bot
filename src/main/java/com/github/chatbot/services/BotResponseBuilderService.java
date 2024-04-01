package com.github.chatbot.services;

import com.github.chatbot.answares.*;
import com.github.chatbot.strategies.AnswareStrategy;
import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import com.github.chatbot.models.facebook.out.MessageResponse;
import com.github.chatbot.util.DetectIntentTexts;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Service
public class BotResponseBuilderService {
    private final List<AnswareStrategy> BOT_ANSWARES;
    public BotResponseBuilderService(){
       BOT_ANSWARES = Arrays.asList(
                new AgeAnswareHandler(),
                new GreetingAnswareHandler(),
                new RequestWeatherAnswareHandler(),
                new NameAnswareHandler(),
                new WeatherInformationAnswareHandler()
        );
    }
    public MessageResponse build(String psid, String text) throws IOException {
        MessageResponse messageBody = buildRequestBody();
        String messageIntent = DetectIntentTexts.getDialogFlowResponse(text).getIntent().getDisplayName();
        String botAnsware = buildBotText(messageIntent, text);

        messageBody.getMessage().setText(botAnsware);
        messageBody.getRecipient().setId(psid);
        return messageBody;
    }
    private MessageResponse buildRequestBody(){
        MessageResponse messageResponse = new MessageResponse();

        messageResponse.setMessage(new MessageRequest());
        messageResponse.setRecipient(new IdRequest());
        return messageResponse;
    }
    public String buildBotText(String intentReturned, String userText) throws IOException {
        String botText = "Desculpe, não entendi";

        for(AnswareStrategy answerer : BOT_ANSWARES){
            if(answerer.hasPattern(intentReturned)){
                botText = answerer.generateResponse(userText);
            }
        }
        return botText;
    }

}