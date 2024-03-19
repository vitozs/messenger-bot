package com.github.chatbot.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BotResponses {

    private static Random rand = new Random();
    private static  List<String> NENHUMA_ALTERNATIVA = Arrays.asList(
            "Oxi, nao entendi",
            "Essa eu nao sei responder",
            "Nao entendi!"
    );

    private static List<String> GREETINGS = Arrays.asList(
            "Opa! Sou um bot do messager, me pergunta alguma coisa!",
            "Ola! Agradeco o contato! Fico a disponha de qualquer duvida",
            "Prazer! Estou disposto a responder suas duvidas referente a mim"
    );
    private static List<String> NOME = Arrays.asList(
            "Me chamo Vitor!",
            "Opa, meu nome é Vitor",
            "Vitor é o meu nome"
    );

    private static List<String> IDADE = Arrays.asList(
            "Eu tenho 18 anos",
            "Claro, eu tenho 18 anos",
            "Tenho 18 anos"
    );

    public static String responderNENHUMA_ALTERNATIVA(){
        return NENHUMA_ALTERNATIVA.get(rand.nextInt(3));
    }

    public static String responderNome(){
        return NOME.get(rand.nextInt(3));
    }

    public static String responderIdade(){
        return IDADE.get(rand.nextInt(3));
    }

    public static String responderGreetings(){
        return GREETINGS.get(rand.nextInt(3));
    }

}
