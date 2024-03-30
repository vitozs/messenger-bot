package com.github.chatbot.services;

import com.github.chatbot.models.dialogFlow.out.UserWeatherData;
import com.github.chatbot.util.DetectIntentTexts;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.protobuf.Struct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class IntentHandlerService {

    DetectIntentTexts detectIntentTexts = new DetectIntentTexts();
    public String getIntentName(String userText) throws IOException {
        return detectIntentTexts.getIntent(userText).getIntent().getDisplayName();
    }

    public UserWeatherData getWeatherData(String userText) throws IOException, ParseException {
        Struct params = getParams(userText);
        UserWeatherData userWeatherData = new UserWeatherData();
        userWeatherData.setCity(getCity(params));
        userWeatherData.setDate(getDate(params));

        return userWeatherData;
    }

    private String getCity(Struct params){
        Struct locations = params;
        return locations.getFieldsOrThrow("city").getStringValue();
    }

    private String getDate(Struct params) throws ParseException {
        String inputDateTime = params.getFieldsOrThrow("date").getStringValue();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date date = inputFormat.parse(inputDateTime);

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = outputFormat.format(date);
        return formattedDate;
    }
    private Struct getParams(String userText) throws IOException {
        QueryResult queryResult = detectIntentTexts.getIntent(userText);
        return queryResult.getParameters();
    }
}
