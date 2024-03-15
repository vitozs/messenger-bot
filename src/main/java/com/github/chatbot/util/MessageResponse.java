package com.github.chatbot.util;

import com.google.gson.JsonObject;

import java.io.IOException;

public class MessageResponse {
    private  String psid;
    private String resposta = "";
    private String message;

    private BotResponses botResponses = new BotResponses();
    public void setStrings(JsonObject jsonObject) throws IOException {
        this.psid = jsonObject.get("entry")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("messaging")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("sender")
                .getAsJsonObject()
                .get("id")
                .getAsString();
        this.message = jsonObject.get("entry")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("messaging")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject()
                .get("message")
                .getAsJsonObject()
                .get("text")
                .getAsString();

        checkMessage();
    };
    public void checkMessage() throws IOException {
        String formatted_msg = message.toLowerCase();
        if(formatted_msg.contains("oi") || formatted_msg.contains("bom dia") || formatted_msg.contains("ola")){
            this.resposta = botResponses.responderGreetings();
        }else if(formatted_msg.contains("nome") || formatted_msg.contains("chama")){
            this.resposta = botResponses.responderNome();
        }else if(formatted_msg.contains("idade") || formatted_msg.contains("anos")){
            this.resposta = botResponses.responderIdade();
        }else{
            this.resposta = botResponses.responderNA();
        }
    }
    public String responseBody(){
        return "{\n" +
                "  \"recipient\":{\n" +
                "    \"id\":\"" + psid + "\"\n" +
                "  },\n" +
                "  \"messaging_type\": \"RESPONSE\",\n" +
                "  \"message\":{\n" +
                "    \"text\":\"" + resposta + "\"\n" +
                "  }\n" +
                "}\n";
    }
}
