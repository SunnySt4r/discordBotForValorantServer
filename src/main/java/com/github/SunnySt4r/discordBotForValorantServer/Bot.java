package com.github.SunnySt4r.discordBotForValorantServer;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.message.MessageCreateEvent;
import org.w3c.dom.events.Event;

public class Bot {
    public Bot(DiscordApi api){
        api.addMessageCreateListener(messageCreateEvent -> {
            System.out.println(messageCreateEvent.getMessageContent());
            switch (messageCreateEvent.getMessageContent()){
                case "!f" -> sendMessage1(messageCreateEvent);
                case "!a" -> sendMessage2(messageCreateEvent);
            }
        });
    }

    public void sendMessage1(MessageCreateEvent event){
        event.getChannel().sendMessage("111");
    }
    public void sendMessage2(MessageCreateEvent event){
        event.getChannel().sendMessage("222");
    }
}
