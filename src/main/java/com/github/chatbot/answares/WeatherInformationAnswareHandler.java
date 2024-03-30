package com.github.chatbot.answares;

import com.github.chatbot.strategies.AnswareStrategy;
import com.github.chatbot.models.wheater.in.WeatherBody;
import com.github.chatbot.services.WeatherConsultingService;
import com.github.chatbot.util.DetectIntentTexts;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.protobuf.Struct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.github.chatbot.util.IntentMatcher.checkIntent;

public class WeatherInformationAnswareHandler implements AnswareStrategy {
    WeatherConsultingService weatherConsultingService = new WeatherConsultingService();
    private String intent = "weatherInformation";
    private String userText;
    private final List<String> WEATHER = Arrays.asList(
            "Claro, me passa o nome da cidade e a data!",
            "Preciso do nome da cidade e a data",
            "Me mande a cidade e a data para verificar a previsão do tempo"
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
    public String generateResponse(String userText){
        try{
            QueryResult queryResult = DetectIntentTexts.getDialogFlowResponse(userText);
            String city = getCity(queryResult.getParameters());
            String date = getDate(queryResult.getParameters());
            WeatherBody weatherBody = weatherConsultingService.getWeatherConditions(city, date);

            return "Em " + city + ", a temperatura máxima atinge " + weatherBody.getMaxTemperature() +
                    " e mínima de " + weatherBody.getMinTemperature() +
                    ", tendo ventos com velocidade média de " + weatherBody.getWindSpeed() + " m/s" ;
        }catch (Exception e){
            return "Verifique se digitou a data e o local corretamente! Lembrando que " +
                    "fornecemos previsão até 1,5 anos á frente da data atual";
        }

    }
    private String getDate(Struct params) throws ParseException {
        String inputDateTime = params.getFieldsOrThrow("date").getStringValue();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date date = inputFormat.parse(inputDateTime);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = outputFormat.format(date);

        return formattedDate;
    }
    private String getCity(Struct params){
        Struct location = params.getFieldsOrThrow("location").getStructValue();
        String city = location.getFieldsOrThrow("city").getStringValue();

        return city;
    }


}
