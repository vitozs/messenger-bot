package com.github.chatbot.services;

import com.github.chatbot.models.dialogFlow.in.Intent;
import com.github.chatbot.models.facebook.in.EventRequest;
import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import com.github.chatbot.models.facebook.out.MessageResponse;
import com.github.chatbot.util.DetectIntentTexts;
import com.github.chatbot.util.IntentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class SendMessageToUserService {
    @Autowired
    private BotResponseBuilderService botResponseBuilderService;
    private final String PAGE_TOKEN = System.getenv("PAGE_TOKEN");
    private final String PAGE_ID = System.getenv("PAGE_ID");
    public void treatAndSendPostRequest(EventRequest request) throws IOException {
        String psid = request.getPSID();
        String text = request.getUserMessage();
        String intent = DetectIntentTexts.getIntent(text);
        MessageResponse response = botResponseBuilderService.build(psid, text, intent);
        sendPostRequest(response);
    }

    private void sendPostRequest(MessageResponse responseBody){
        WebClient client =  WebClient.builder()
                .baseUrl("https://graph.facebook.com/v19.0/" + PAGE_ID)
                .build();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/messages")
                        .queryParam("access_token", PAGE_TOKEN )
                        .build())
                .body(BodyInserters.fromValue(responseBody))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    System.out.println("Resposta do servidor: " + response);
                });
    }

}
