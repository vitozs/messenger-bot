package com.github.chatbot.services;

import com.github.chatbot.models.facebook.in.EventRequest;
import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import com.github.chatbot.models.facebook.out.MessageRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URISyntaxException;

@Service
public class MessageSenderService {

    @Autowired
    BotResponseBuilderService botResponseBuilderService;
    public void treatAndSendPostRequest(EventRequest request) throws URISyntaxException {
        String psid = request.getEntry()
                .get(0)
                .getMessaging()
                .get(0)
                .getSender()
                .getId();

        String text = request.getEntry()
                .get(0)
                .getMessaging()
                .get(0)
                .getMessage()
                .getText();

        sendPostRequest(buildResponseBody(psid, text));
    }

    public MessageRequestResponse buildResponseBody(String psid, String userText){
        String botResponse = botResponseBuilderService.buildBotText(userText);
        String responseText = botResponse;
        MessageRequestResponse messageRequestResponse = new MessageRequestResponse();
        messageRequestResponse.setMessage(new MessageRequest(responseText));
        messageRequestResponse.setRecipient(new IdRequest(psid));
        return messageRequestResponse;
    }

    public void sendPostRequest(MessageRequestResponse responseBody) throws URISyntaxException {
        final String PAGE_TOKEN = System.getenv("PAGE_TOKEN");
        WebClient client =  WebClient.builder()
                .baseUrl("https://graph.facebook.com/v19.0/206424369230843")
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
