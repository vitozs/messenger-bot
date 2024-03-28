package com.github.chatbot.models.dialogFlow.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Intent {
    private String displayName;
}
