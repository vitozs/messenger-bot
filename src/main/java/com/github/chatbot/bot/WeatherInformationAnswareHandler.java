package com.github.chatbot.bot;

import com.github.chatbot.models.dialogFlow.out.UserWeatherData;
import com.github.chatbot.models.wheater.in.WeatherBody;
import com.github.chatbot.services.IntentHandlerService;
import com.github.chatbot.services.WeatherConsultingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static com.github.chatbot.util.IntentMatcher.checkIntent;

public class WeatherInformationAnswareHandler implements AnswareStrategy{
    WeatherConsultingService weatherConsultingService = new WeatherConsultingService();
    IntentHandlerService intentHandlerService = new IntentHandlerService();
    private String intent = "weatherInformation";
    private String userText;
    private final List<String> WEATHER = Arrays.asList(
            "Claro, me passa o nome da cidade e a data!",
            "Preciso do local e o dia",
            "Me mande o local e o dia para verificar a previsão do tempo"
    );
    @Override
    public void setUserText(String userText) {
        this.userText = userText;
    }
    @Override
    public boolean hasPattern(String intentReturned) {
        return checkIntent(intent, intentReturned);
    }

    @Override
    public String generateResponse(String userText) {

        String city = null;
        String date = null;
        try {
            UserWeatherData userWeatherData = intentHandlerService.getWeatherData(userText);
            city = userWeatherData.getCity();
            date = userWeatherData.getDate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        WeatherBody weatherBody = weatherConsultingService.getWeatherConditions(city, date);

        return "Em " + city + ", a temperatura máxima atinge " + weatherBody.getMaxTemperature() + " e mínima de " + weatherBody.getMinTemperature() + ", tendo ventos com " + weatherBody.getWindSpeed() ;

    }

}
