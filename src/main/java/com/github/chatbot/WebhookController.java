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

    private static final String PAGE_ACCESS_TOKEN = "EAANItlDtKeEBOZCIKQDqRDXpZBPCQ5QDlZCYfZAQ0s2De5DHSCO9192tCUhVvfOPGGsp6ahlJYJz8ZBwQgkAlOOPbVbFVIEAqUZCGI5tlWzyhzbjUif7fMRZBAvRZCj9ifBwBI7qR146hnpUCFqpikooDM3DydhZAjxZAaDG1G4dczqmvZAk4tjn2W4RehBZCoZAoZBWc960GBMefenoRy8ZBMxPbUqCPAZD";

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


            String psid = jsonObject.get("entry")
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

            String message = jsonObject.get("entry")
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

                System.out.println(psid + " " + message);
            String body = "{\n" +
                    "  \"recipient\":{\n" +
                    "    \"id\":\"7397663706980330\"\n" +
                    "  },\n" +
                    "  \"messaging_type\": \"RESPONSE\",\n" +
                    "  \"message\":{\n" +
                    "    \"text\":\"AUTOMATICO\"\n" +
                    "  }\n" +
                    "}\n";
            URL url = new URL("https://graph.facebook.com/v19.0/206424369230843/messages?access_token=" + PAGE_ACCESS_TOKEN);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "application/json");
            try(DataOutputStream dos = new DataOutputStream(conn.getOutputStream())){
                dos.writeBytes(body);
            }
            try(BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
                String line;
                while((line = bf.readLine()) != null){
                    System.out.println(line);
                }
            }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
