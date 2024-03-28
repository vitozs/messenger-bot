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

        for(AnswareStrategy answare : answaresCollection.getAnswares()){
            if(answare.hasPattern(intentReturned)){
                botText = answare.generateResponse();
            }
        }
        return botText;
    }

    public InputBody buildInputBodyForDialogFlow(String userText){
        InputBody inputBody = new InputBody();
        QueryInput queryInput = new QueryInput();

        queryInput.setText(new Text());
        queryInput.getText().setText(userText);
        inputBody.setQueryInput(queryInput);
        return inputBody;
    }
}