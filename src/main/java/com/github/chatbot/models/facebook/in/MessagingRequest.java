package com.github.chatbot.models.facebook.in;

import lombok.Data;

@Data
public class MessagingRequest {
    private IdRequest sender;
    private IdRequest recipient;
    private MessageRequest message;
}
