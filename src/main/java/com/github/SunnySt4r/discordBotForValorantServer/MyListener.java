package com.github.SunnySt4r.discordBotForValorantServer;

import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MyListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        User user = event.getMessageAuthor().asUser().get();
        if(user.getNicknameMentionTag().equals("<@!282914050566914049>")){
            sendMessage1(event);
        }else if(user.getNicknameMentionTag().equals("<@!694899865670647848>")){
            messageForMatvey(event);
        }
    }

    private void messageForMatvey(MessageCreateEvent event) {
        User user =event.getMessageAuthor().asUser().get();
        user.getPrivateChannel().get().sendMessage("ЛОХ ПоЛуЧаЕтСя)");
    }

    public void sendMessage1(MessageCreateEvent event){
        event.getChannel().sendMessage("111");
    }

    public void sendMessage2(MessageCreateEvent event){
        event.getChannel().sendMessage("222");
    }
}
