package com.github.chatbot.services;

import com.github.chatbot.models.facebook.in.EventRequest;
import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import com.github.chatbot.models.facebook.out.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URISyntaxException;

@Service
public class MessageSenderService {
    @Autowired
    private BotResponseBuilderService botResponseBuilderService;
    private final String PAGE_TOKEN = System.getenv("PAGE_TOKEN");
    private final String PAGE_ID = System.getenv("PAGE_ID");
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
    private MessageResponse buildResponseBody(String psid, String userText){
        String responseText = botResponseBuilderService.buildBotText(userText);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage(new MessageRequest(responseText));
        messageResponse.setRecipient(new IdRequest(psid));
        return messageResponse;
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
