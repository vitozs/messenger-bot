package com.github.chatbot.models.dialogFlow.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class QueryResult {
    private String queryText;
    private String fulfillmentText;
    private Intent intent;
}
