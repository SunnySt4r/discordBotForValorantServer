package com.github.SunnySt4r.discordBotForValorantServer.Listeners;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.channel.server.voice.ServerVoiceChannelMemberJoinEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelMemberJoinListener;

public class MyVoiceChannelListener implements ServerVoiceChannelMemberJoinListener{
    @Override
    public void onServerVoiceChannelMemberJoin(ServerVoiceChannelMemberJoinEvent joinEvent) {
        if(joinEvent.getChannel().getId() == 1028687107914477678L){
            createVoiceChannel(joinEvent);
        }
    }

    public void createVoiceChannel(ServerVoiceChannelMemberJoinEvent joinEvent){
        User user = joinEvent.getUser();
        Server server = joinEvent.getServer();
        ServerVoiceChannel channel = new ServerVoiceChannelBuilder(server)
                .setName( "Team#" + (int) (Math.random()*1000))
                .setUserlimit(5)
                .setCategory(server.getChannelCategoryById(999107653802393680L).get())
                .create()
                .join();

        user.move(channel);

        channel.addServerVoiceChannelMemberLeaveListener(event -> {
            if(event.getChannel().getConnectedUserIds().isEmpty()){
                event.getChannel().delete();
            }
        });
    }
}
