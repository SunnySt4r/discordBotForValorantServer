package com.github.SunnySt4r.discordBotForValorantServer;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.message.MessageCreateEvent;

public class Bot {
    public Bot(DiscordApi api){
        api.addMessageCreateListener(new MyListener());
//        api.addMessageCreateListener(messageCreateEvent -> {
//            System.out.println(messageCreateEvent.getMessageContent());
//            switch (messageCreateEvent.getMessageContent()){
//                case "!f" -> sendMessage1(messageCreateEvent);
//                case "!a" -> sendMessage2(messageCreateEvent);
//            }
//        });
    }

    }
