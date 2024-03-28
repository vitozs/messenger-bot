package com.github.chatbot.bot;

import com.github.chatbot.models.wheater.out.WeatherBodyResponse;
import com.github.chatbot.services.WeatherConsultingService;

import static com.github.chatbot.util.IntentMatcher.checkIntent;


public class WeatherAnswareHandler implements AnswareStrategy {

    private String intent = "weatherInformation";
    private WeatherConsultingService weatherConsultingService = new WeatherConsultingService();

    @Override
    public boolean hasPattern(String intentReturned) {
        return checkIntent(intent, intentReturned);
    }

    @Override
    public String generateResponse() {
        //WeatherBodyResponse body = weatherConsultingService.consultWeatherByCity("poa");
        //WeatherDataResponse weatherData = body.getData();

        return "TEMPO";
    }
}