package com.github.SunnySt4r.discordBotForValorantServer.Listeners;

import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

import java.util.NoSuchElementException;

public class MyReactionListener implements ReactionAddListener, ReactionRemoveListener{

    @Override
    public void onReactionAdd(ReactionAddEvent addEvent) {
        try{
            Server server = addEvent.getServer().get();
            System.out.println(1);
            if(addEvent.getMessageId() == 1027173185789112412L){
                if(server.getRoleById(722350286072578048L).isPresent()){
                    Role role = server.getRoleById(722350286072578048L).get();
                }
            }
        }catch (NoSuchElementException exception) {
            System.out.println(1 + " " + exception.getMessage());
        }
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent removeEvent) {

    }
}
