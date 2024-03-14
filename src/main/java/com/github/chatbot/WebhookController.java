package com.github.chatbot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@RestController
public class WebhookController {

    public WebhookService webhookService = new WebhookService();
    @GetMapping("/webhook")
    public ResponseEntity<String> teste(@RequestParam("hub.mode") String hub, @RequestParam("hub.challenge") String challange, @RequestParam("hub.verify_token") String token ){
        if(Objects.equals(token, "banana")){
            return new ResponseEntity<>(challange, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> teste2(HttpEntity<String> request) throws IOException {
        String json = request.getBody();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        if(Objects.equals(jsonObject.get("object").getAsString(), "page" )){
            webhookService.setStrings(jsonObject);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
