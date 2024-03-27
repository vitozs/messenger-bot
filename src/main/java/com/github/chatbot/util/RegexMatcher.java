package com.github.chatbot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static boolean regexMatchWithInput(String regex, String userInput){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInput);
        return matcher.find();
    }
}
