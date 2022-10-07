package com.github.SunnySt4r.discordBotForValorantServer.Listeners;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.event.message.reaction.SingleReactionEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

public class MyReactionListener implements ReactionAddListener, ReactionRemoveListener{

    @Override
    public void onReactionAdd(ReactionAddEvent addEvent) {
        Channel channel = addEvent.getChannel();
        if(channel.getId() == 1027172565271199774L){
            Server server = addEvent.getServer().get();
            if(addEvent.getMessageId() == 1027173185789112412L){
                useRole1(addEvent, server,true);
            }
        }
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent removeEvent) {
        Channel channel = removeEvent.getChannel();
        if(channel.getId() == 1027172565271199774L){
            Server server = removeEvent.getServer().get();
            if(removeEvent.getMessageId() == 1027173185789112412L){
                useRole1(removeEvent, server,false);
            }
        }
    }

    public void useRole1(SingleReactionEvent event, Server server, boolean addOrRemove){
        if(server.getRoleById(722350286072578048L).isPresent()){
            Role role = server.getRoleById(722350286072578048L).get();
            if(addOrRemove){
                role.addUser(event.getUser().get());
            }else{
                role.removeUser(event.getUser().get());
            }
        }
    }
}
