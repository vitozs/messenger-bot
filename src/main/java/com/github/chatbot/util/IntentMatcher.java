package com.github.chatbot.util;

import java.util.Objects;

public class IntentMatcher {
    public static boolean checkIntent(String intent, String expectedIntent){
        return Objects.equals(intent, expectedIntent);
    }

}
