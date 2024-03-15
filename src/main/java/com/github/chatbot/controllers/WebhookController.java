package com.github.chatbot.controllers;

import com.github.chatbot.services.WebhookService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

@RestController
public class WebhookController {

    @Autowired
    public WebhookService webhookService;
    @GetMapping("/webhook")
    public ResponseEntity<String> teste(@RequestParam("hub.mode") String hub, @RequestParam("hub.challenge") String challange, @RequestParam("hub.verify_token") String token ){
        if(Objects.equals(token, "banana")){
            return new ResponseEntity<>(challange, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> teste2(HttpEntity<String> request) throws IOException, URISyntaxException, InterruptedException {
        String json = request.getBody();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        if(Objects.equals(jsonObject.get("object").getAsString(), "page" )){
            webhookService.sendMessage(jsonObject);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
