package com.github.chatbot.util;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DetectIntentTexts {

    // DialogFlow API Detect Intent sample with text inputs.
    public static String getIntent(String text) throws IOException, ApiException {
        String intent;
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of("roboto-dkye", "123456789");
            System.out.println("Session Path: " + session.toString());


            TextInput.Builder textInput =
                    TextInput.newBuilder().setText(text).setLanguageCode("pt-br");

            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();

            intent = queryResult.getIntent().getDisplayName();

        }
        return intent;
    }
}