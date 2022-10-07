package com.github.SunnySt4r.discordBotForValorantServer;

import com.github.SunnySt4r.discordBotForValorantServer.Listeners.MyMessageCreateListener;
import com.github.SunnySt4r.discordBotForValorantServer.Listeners.MyReactionListener;
import org.javacord.api.DiscordApi;

public class Bot {
    public Bot(DiscordApi api){
        MyReactionListener myReactionListener = new MyReactionListener();

        api.addMessageCreateListener(new MyMessageCreateListener());
        api.addReactionAddListener(myReactionListener);
        api.addReactionRemoveListener(myReactionListener);
    }
}
