package com.github.chatbot.models.facebook.in;

import lombok.Data;

import java.util.List;

@Data
public class EntryRequest {
    private String id;
    private Long time;
    private List<MessagingRequest> messaging;


}
