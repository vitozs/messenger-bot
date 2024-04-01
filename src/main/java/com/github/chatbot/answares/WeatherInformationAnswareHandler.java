package com.github.chatbot.answares;

import com.github.chatbot.exceptions.CityOrDateInvalid;
import com.github.chatbot.models.wheater.in.WeatherBody;
import com.github.chatbot.services.WeatherConsultingService;
import com.github.chatbot.strategies.AnswareStrategy;
import com.github.chatbot.util.DetectIntentTexts;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.protobuf.Struct;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.github.chatbot.util.IntentMatcher.checkIntent;

public class WeatherInformationAnswareHandler implements AnswareStrategy {
    private final WeatherConsultingService weatherConsultingService;
    private final String intent = "weatherInformation";
    public WeatherInformationAnswareHandler(){
        weatherConsultingService= new WeatherConsultingService();
    }
    @Override
    public boolean hasPattern(String intentReturned) {
        return checkIntent(intent, intentReturned);
    }
    @Override
    public String generateResponse(String userText) throws IOException {
        try{
            QueryResult queryResult = DetectIntentTexts.getDialogFlowResponse(userText);
            String city = getCity(queryResult.getParameters());
            String date = getDate(queryResult.getParameters());
            WeatherBody weatherBody = weatherConsultingService.getWeatherConditions(city, date);

            return responseMessage(city, date, weatherBody);
        }catch (Exception e){
            return e.getMessage();
        }

    }
    private String getDate(Struct params) {
        try{
            String inputDateTime = params.getFieldsOrThrow("date").getStringValue();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            Date date = inputFormat.parse(inputDateTime);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = outputFormat.format(date);
            return formattedDate;
        }catch (ParseException e){
            throw new CityOrDateInvalid("Formato de data inválida! Verifique e reescreva novamente");
        }
    }
    private String getCity(Struct params){
        Struct location = params.getFieldsOrThrow("location").getStructValue();
        String city = location.getFieldsOrThrow("city").getStringValue();
        return city;
    }
    private String responseMessage(String city, String date, WeatherBody weather){
       return "Em " + city + ", no dia " + date + " a temperatura máxima atinge " + weather.getMaxTemperature() +
                " e mínima de " + weather.getMinTemperature() +
                ", tendo ventos com velocidade média de " + weather.getWindSpeed() + " m/s" ;
    }

}
