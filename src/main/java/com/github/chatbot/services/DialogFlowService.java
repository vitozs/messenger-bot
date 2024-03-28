package com.github.chatbot.services;

import com.github.chatbot.models.dialogFlow.in.DialogBody;
import com.github.chatbot.models.dialogFlow.out.Fulillment;
import com.github.chatbot.models.dialogFlow.out.InputBody;
import com.github.chatbot.models.dialogFlow.out.QueryInput;
import com.github.chatbot.models.dialogFlow.out.Text;
import com.github.chatbot.models.wheater.out.WeatherBodyResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class DialogFlowService {
    WeatherConsultingService weatherConsultingService = new WeatherConsultingService();
    public Fulillment checkIntentOfMessage(DialogBody request){
        String intent = request.getQueryResult().getIntent().getDisplayName();
        String userText = request.getQueryResult().getQueryText();

        System.out.println(userText);
        if(isWeatherRequest(intent)){
            return buildWeatherResponse(userText);
        }
        return null;
    }
    public Fulillment buildWeatherResponse(String userText){
        Fulillment fulillment = new Fulillment();
        WeatherBodyResponse weatherBodyResponse = weatherConsultingService.consultWeatherByCity(userText);

        String response = "País: " + weatherBodyResponse.getCountry() + "\n" +
                "Cidade: " + weatherBodyResponse.getName() + "\n" +
                "Temp: " + weatherBodyResponse.getTemp();
        fulillment.setFulfillmentText(response);
        return fulillment;
    }
    public boolean isWeatherRequest(String intent){
        return Objects.equals(intent, "weatherInformation");
    }
    public String getDialogFlowResponseMessage(String userText){
        InputBody inputObject = buildInputBodyForDialogFlow(userText);
        return sendPostRequestAndGetResponse(inputObject);
    }
    public InputBody buildInputBodyForDialogFlow(String userText){
        InputBody inputBody = new InputBody();
        QueryInput queryInput = new QueryInput();

        queryInput.setText(new Text());
        queryInput.getText().setText(userText);
        inputBody.setQueryInput(queryInput);
        return inputBody;
    }
    public String sendPostRequestAndGetResponse(InputBody request){
        WebClient client =  WebClient.builder()
                .build();

        ResponseEntity<DialogBody> dialogBodyMono = client.post()
                .uri("https://dialogflow.googleapis.com/v2/projects/roboto-dkye/agent/sessions/ce7d410f-8db2-fe33-cda4-a93de5fa1d1e:detectIntent")
                .header(HttpHeaders.AUTHORIZATION, "Bearer ya29.a0Ad52N3_T5h7DkhPvmqspNLmjmqVIV9Hci30U4-x95p85uexKtmRpGiitsZO-kWXcJ75-luE-X61dREwxkWSbd1qh04xG6k7tF95xhO-3OOotqQrV2itzEY9b7QL4msv9L7ROSFdH7CjcyaPMNcaG-SKx1EIsMWIbcAWdHZhwTSqs1TfwZkaBdENbFu6uz3ZBi09ht7sOGK2-gbpiCUD0RMjxQFjEbmrQKr7ABkULjg1yX7j9YVIywVomn29hK1cNlsMXhxS2FhmMX-WJsDpB6hl4r0w80hG29wNpexdBcJcOlYIyHMWtYpNF6kknBSMFKbTKcBmYg2fv3-iInMG-40eWmK1VufMJ-8GtyxoIKlVBiUsj0s00ERxytoJt9cMYI-ALaKU_jimn0oeaGuY3fZNq3BTyOV_uXK_aaCgYKAdESARISFQHGX2MierxI5E90ys5c6ofqCPtHJg0427")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .toEntity(DialogBody.class).block();

        return dialogBodyMono.getBody().getQueryResult().getFulfillmentText();
    }
}
