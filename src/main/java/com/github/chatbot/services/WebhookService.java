package com.github.chatbot.services;

import com.github.chatbot.util.MessageResponse;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WebhookService {
    private static final String PAGE_ACCESS_TOKEN = "EAANItlDtKeEBO9vOrHSxSGNZBHOBSIZCI0ZCNKJlSC4I5ijKilZA2H5d1LHLa29I4JIh9VaCGZCgizuPYqm9zIkna1OfI5LmDFojy4jGoUOVn3UGuOcrT2S7bFVZBf7pUXFcLqTagkZC4mhlpqemzAIQSdj3zMinC0pnT2ZACoX29NzIbY6jSqGF8GRRBxX3cIDhNhpz1odEZBZB7OoUmzR0UhviRE";

    public void sendMessage(JsonObject jsonObject) throws IOException, URISyntaxException, InterruptedException {
        MessageResponse messageModel = new MessageResponse();
        messageModel.setStrings(jsonObject);

        HttpClient client = HttpClient.newHttpClient();

        //monta a requisicao
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://graph.facebook.com/v19.0/206424369230843/messages?access_token=" + PAGE_ACCESS_TOKEN))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(messageModel.responseBody()))
                .build();

        //envia a requisicao
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //processa a resposta
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }

}
