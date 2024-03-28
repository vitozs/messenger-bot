package com.github.chatbot.controllers;

import com.github.chatbot.models.dialogFlow.in.DialogBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DialogFlowController {
    @PostMapping("/dialog")
    ResponseEntity<?> dialogFlow(@RequestBody DialogBody queryResult){
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
