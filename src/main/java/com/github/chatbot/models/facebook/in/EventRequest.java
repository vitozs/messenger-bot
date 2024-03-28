package com.github.chatbot.models.facebook.in;

import lombok.Data;

import java.util.List;

@Data
public class EventRequest {
    private String object;
    private List<EntryRequest> entry;
    public String getUserMessage(){
        return entry.get(0).getMessaging().get(0).getMessage().getText();
    }
    public String getPSID(){
        return entry.get(0).getMessaging().get(0).getSender().getId();
    }
}
