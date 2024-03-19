package com.github.chatbot.models.facebook.in;

import lombok.Data;

import java.util.List;

@Data
public class EventRequest {
    private String object;
    private List<EntryRequest> entry;
}
