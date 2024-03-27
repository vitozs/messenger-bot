package com.github.chatbot.bot;

import com.github.chatbot.models.wheater.out.WeatherBodyResponse;
import com.github.chatbot.models.wheater.out.WeatherDataResponse;
import com.github.chatbot.services.WeatherConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.chatbot.util.RegexMatcher.regexMatchWithInput;


public class WeatherAnswareHandler implements Answare{

    private String regex = "\\b(?:tempo)\\b";
    private WeatherConsultingService weatherConsultingService = new WeatherConsultingService();

    @Override
    public boolean hasPattern(String userInput) {
        return regexMatchWithInput(regex, userInput);
    }

    @Override
    public String generateResponse() {
        WeatherBodyResponse body = weatherConsultingService.consultWeatherByCityId();
        WeatherDataResponse weatherData = body.getData();

        return "Cidade: " + body.getName() + "\n" +
                "Condicao: " + weatherData.getCondition();
    }
}
