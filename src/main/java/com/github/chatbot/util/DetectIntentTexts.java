package com.github.chatbot.util;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;

import java.io.IOException;

public class DetectIntentTexts {

    public static QueryResult getDialogFlowResponse(String text) throws IOException, ApiException {
        QueryResult queryResult;
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of("roboto-dkye", "123456789");
            TextInput.Builder textInput =
                    TextInput.newBuilder().setText(text).setLanguageCode("pt-br");

            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

            queryResult = response.getQueryResult();
        }
        return queryResult;
    }




}
