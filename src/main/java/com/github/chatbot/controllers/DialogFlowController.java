package com.github.chatbot.controllers;

import com.github.chatbot.models.dialogFlow.in.DialogBody;
import com.github.chatbot.services.DialogFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DialogFlowController {
    @Autowired
    DialogFlowService dialogFlowService;
    @PostMapping("/dialog")
    ResponseEntity<?> dialogFlow(@RequestBody DialogBody queryResult){
        return new ResponseEntity<>(dialogFlowService.checkIntentOfMessage(queryResult), HttpStatus.OK);

    }

}
