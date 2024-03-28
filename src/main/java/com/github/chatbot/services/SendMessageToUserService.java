package com.github.chatbot.services;

import com.github.chatbot.models.facebook.in.EventRequest;
import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import com.github.chatbot.models.facebook.out.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SendMessageToUserService {
    @Autowired
    private DialogFlowService dialogFlowService;
    private final String PAGE_TOKEN = System.getenv("PAGE_TOKEN");
    private final String PAGE_ID = System.getenv("PAGE_ID");
    public void treatAndSendPostRequest(EventRequest request){
        String psid = request.getPSID();
        String text = request.getUserMessage();

        sendPostRequest(buildRequestBody(psid, text));
    }
    private MessageResponse buildRequestBody(String psid, String userText){
        String responseText = getBotResponse(userText);
        MessageResponse messageResponse = new MessageResponse();

        messageResponse.setMessage(new MessageRequest(responseText));
        messageResponse.setRecipient(new IdRequest(psid));
        return messageResponse;
    }
    private String getBotResponse(String userText){
        return dialogFlowService.getDialogFlowResponseMessage(userText);
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
