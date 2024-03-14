package com.github.chatbot;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WebhookService {
    private static final String PAGE_ACCESS_TOKEN = "EAANItlDtKeEBO2BbvIFGJUJNU22580QPLAKG8CauZBDLcnZAZAqSFEy4MxBaAZBUYWKfsNHX7WpALa8YLG1LQPWulwQGXnXm3zYgdP00yc1JqToCs5AFKOu9OvPPS8M65U2MiQG4XjZC3qqwMgkmfbKxH0ozOq0aBfPW4ZBvuvMo2qaw3a2SttfG5UMhB6jFEsP0uVa76l2jJ5osRpZBXELbV0ZD";
    private  String psid;
    private String msg = "";
    private String message;
    private String body = "Desculpe, nao entendi";
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
        if(message.contains("nome") || message.contains("chama")){
            msg = "Me chamo Vitor";
        }else if(message.contains("idade") || message.contains("anos")){
            msg = "Tenho 18 anos";
        }else{
            msg = "Nao entendi";
        }
        sendMessage();
    }
    public void sendMessage() throws IOException {
        URL url = new URL("https://graph.facebook.com/v19.0/206424369230843/messages?access_token=" + PAGE_ACCESS_TOKEN);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-type", "application/json");
        try(DataOutputStream dos = new DataOutputStream(conn.getOutputStream())){
            dos.writeBytes(body(msg));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
            String line;
            while((line = bf.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String body(String mensagem){
       return "{\n" +
                "  \"recipient\":{\n" +
                "    \"id\":\"" + psid + "\"\n" +
                "  },\n" +
                "  \"messaging_type\": \"RESPONSE\",\n" +
                "  \"message\":{\n" +
                "    \"text\":\"" + mensagem + "\"\n" +
                "  }\n" +
                "}\n";
    }
}
