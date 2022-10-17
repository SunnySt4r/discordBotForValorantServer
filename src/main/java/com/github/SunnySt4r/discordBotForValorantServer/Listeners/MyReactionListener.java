package com.github.SunnySt4r.discordBotForValorantServer.Listeners;

import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.event.message.reaction.SingleReactionEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

import java.util.HashMap;

public class MyReactionListener implements ReactionAddListener, ReactionRemoveListener{

    HashMap<Long, Long> roles = new HashMap<>() {{
        put(1029641415610859530L, 1029642001278308422L); //omen
        put(1029641447613411439L, 1029642204878217318L); //raze
    }};

    @Override
    public void onReactionAdd(ReactionAddEvent addEvent) {
        Channel channel = addEvent.getChannel();
        if(channel.getId() == 1029641373860765706L){
            useRole(addEvent, true);
        }
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent removeEvent) {
        Channel channel = removeEvent.getChannel();
        if(channel.getId() == 1029641373860765706L){
            useRole(removeEvent,false);
        }
    }

    public void useRole(SingleReactionEvent event, boolean addOrRemove){
        Server server = event.getServer().get();
        if(server.getRoleById(roles.get(event.getMessageId())).isPresent()){
            Role role = server.getRoleById(roles.get(event.getMessageId())).get();
            if(addOrRemove){
                role.addUser(event.getUser().get());
            }else{
                role.removeUser(event.getUser().get());
            }
        }
    }
}
