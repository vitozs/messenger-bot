package com.github.chatbot.services;

import com.github.chatbot.models.facebook.in.EventRequest;
import com.github.chatbot.models.facebook.out.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.text.ParseException;

@Service
public class SendMessageToUserService {
    @Autowired
    private BotResponseBuilderService botResponseBuilderService;
    @Value("${facebook.apikey}")
    private String PAGE_TOKEN;
    @Value("${facebook.page.id}")
    private String PAGE_ID;
    public void treatAndSendPostRequest(EventRequest request) throws IOException, ParseException {
        String psid = request.getPSID();
        String text = request.getUserMessage();
        MessageResponse response = botResponseBuilderService.build(psid, text);
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
