package com.github.chatbot.services;

import com.github.chatbot.models.wheater.out.WeatherBodyResponse;
import com.github.chatbot.models.wheater.out.WeatherDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class BotConfigService {
    @Autowired
    private WeatherConsultingService weatherConsultingService;
    private Random rand = new Random();
    private List<String> NO_ALTERNATIVE = Arrays.asList(
            "Oxi, nao entendi",
            "Essa eu nao sei responder",
            "Nao entendi!"
    );
    private List<String> GREETING = Arrays.asList(
            "Opa! Sou um bot do messager, me pergunta alguma coisa!",
            "Ola! Agradeco o contato! Fico a disponha de qualquer duvida",
            "Prazer! Estou disposto a responder suas duvidas referente a mim"
    );
    private List<String> NAME = Arrays.asList(
            "Me chamo Vitor!",
            "Opa, meu nome é Vitor",
            "Vitor é o meu nome"
    );

    private List<String> AGE = Arrays.asList(
            "Eu tenho 18 anos",
            "Claro, eu tenho 18 anos",
            "Tenho 18 anos"
    );
    public String respondIfNoAlternative(){
        return NO_ALTERNATIVE.get(rand.nextInt(3));
    }
    public String respondIfName(){
        return NAME.get(rand.nextInt(3));
    }
    public String respondIfAge(){
        return AGE.get(rand.nextInt(3));
    }
    public String respondIfGreetings(){
        return GREETING.get(rand.nextInt(3));
    }
    public String respondIfWeather(){
        WeatherBodyResponse body = weatherConsultingService.consultWeatherByCityId();
        WeatherDataResponse weatherData = body.getData();

        return "Cidade: " + body.getName() + "\n" +
                "Condicao: " + weatherData.getCondition();
    }
}
