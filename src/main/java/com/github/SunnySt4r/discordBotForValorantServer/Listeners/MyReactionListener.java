package com.github.SunnySt4r.discordBotForValorantServer.Listeners;

import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

import java.util.Optional;

public class MyReactionListener implements ReactionAddListener, ReactionRemoveListener {

    @Override
    public void onReactionAdd(ReactionAddEvent addEvent) {
        if(addEvent.getMessage().get().getIdAsString().equals("1027173185789112412")){
        }
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent removeEvent) {

    }
}
