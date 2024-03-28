package com.github.chatbot.models.dialogFlow.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class QueryInput {
    private Text text;
}
