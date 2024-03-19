package com.github.chatbot.models.facebook.out;

import com.github.chatbot.models.facebook.in.IdRequest;
import com.github.chatbot.models.facebook.in.MessageRequest;
import lombok.Data;

@Data
public class MessageResponse {
    private IdRequest recipient;
    private String messaging_type = "RESPONSE";
    private MessageRequest message;
}
