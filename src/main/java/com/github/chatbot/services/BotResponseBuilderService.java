package com.github.chatbot.services;

import com.github.chatbot.strategies.AnswareStrategy;
import com.github.chatbot.answares.AnswaresCollection;
import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import com.github.chatbot.models.facebook.out.MessageResponse;
import com.github.chatbot.util.DetectIntentTexts;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
public class BotResponseBuilderService {
    AnswaresCollection answaresCollection = new AnswaresCollection();

    public MessageResponse build(String psid, String text) throws IOException, ParseException {
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

    public String buildBotText(String intentReturned, String userText){
        String botText = "Não entendi!";

        for(AnswareStrategy answerer : answaresCollection.getAnswares()){
            answerer.setUserText(userText);
            if(answerer.hasPattern(intentReturned)){
                botText = answerer.generateResponse(userText);
            }
        }
        return botText;
    }

}