package com.github.chatbot.models.dialogFlow.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
public class Text {
    private String text;
    private final String languageCode = "pt-br";
}
