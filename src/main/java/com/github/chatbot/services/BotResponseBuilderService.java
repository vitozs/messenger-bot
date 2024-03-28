package com.github.chatbot.services;

import com.github.chatbot.bot.AnswareStrategy;
import com.github.chatbot.bot.AnswaresCollection;
import com.github.chatbot.models.dialogFlow.out.InputBody;
import com.github.chatbot.models.dialogFlow.out.QueryInput;
import com.github.chatbot.models.dialogFlow.out.Text;
import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import com.github.chatbot.models.facebook.out.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotResponseBuilderService {

    AnswaresCollection answaresCollection = new AnswaresCollection();

    public MessageResponse build(String psid, String text, String intent){
        MessageResponse messageBody = buildRequestBody();
        String messageIntent = intent;
        String botAnsware = buildBotText(messageIntent);
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

    public String buildBotText(String intentReturned) {
        String botText = "Não entendi!";

        for(AnswareStrategy answerer : answaresCollection.getAnswares()){
            if(answerer.hasPattern(intentReturned)){
                botText = answerer.generateResponse();
            }
        }
        return botText;
    }
}