package com.github.chatbot.models.dialogFlow.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter

public class DialogBody {
    private QueryResult queryResult;
}
