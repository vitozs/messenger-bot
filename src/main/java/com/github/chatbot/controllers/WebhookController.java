package com.github.chatbot.controllers;

import com.github.chatbot.models.facebook.in.EventRequest;
import com.github.chatbot.services.SendMessageToUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class WebhookController {

    @Autowired
    public SendMessageToUserService sendMessageToUserService;
    @GetMapping("/webhook")
    public ResponseEntity<String> verifyChallange(@RequestParam("hub.mode") String hub, @RequestParam("hub.challenge") String challange, @RequestParam("hub.verify_token") String token ){
        if(Objects.equals(token, "banana")){
            return new ResponseEntity<>(challange, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    @PostMapping("/webhook")
    public ResponseEntity<String> botMessengerWebhook(@RequestBody EventRequest request){
        if(Objects.equals(request.getObject(), "page" )){
            sendMessageToUserService.treatAndSendPostRequest(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
