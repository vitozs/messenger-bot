package com.github.chatbot.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BotResponses {

    private Random rand = new Random();
    private List<String> NA = Arrays.asList(
            "Oxi, nao entendi",
            "Essa eu nao sei responder",
            "Nao entendi!"
    );

    private List<String> GREETINGS = Arrays.asList(
            "Opa! Sou um bot do messager, me pergunta alguma coisa!",
            "Ola! Agradeco o contato! Fico a disponha de qualquer duvida",
            "Prazer! Estou disposto a responder suas duvidas referente a mim"
    );
    private List<String> NOME = Arrays.asList(
            "Me chamo Vitor!",
            "Opa, meu nome é Vitor",
            "Vitor é o meu nome"
    );

    private List<String> IDADE = Arrays.asList(
            "Eu tenho 18 anos",
            "Claro, eu tenho 18 anos",
            "Tenho 18 anos"
    );

    public String responderNA(){
        return NA.get(rand.nextInt(3));
    }

    public String responderNome(){
        return NOME.get(rand.nextInt(3));
    }

    public String responderIdade(){
        return IDADE.get(rand.nextInt(3));
    }

    public String responderGreetings(){
        return GREETINGS.get(rand.nextInt(3));
    }

}
