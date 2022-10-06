package com.github.SunnySt4r.discordBotForValorantServer.Listeners;

import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MyMessageCreateListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        User user = event.getMessageAuthor().asUser().get();
        if(user.getId() == 282914050566914049L){
            sendMessage1(event);
        }else if(user.getId() == 694899865670647848L){
            messageForMatvey(event);
        }
    }

    private void messageForMatvey(MessageCreateEvent event) {
        User user = event.getMessageAuthor().asUser().get();
        user.getPrivateChannel().get().sendMessage("ЛОХ ПоЛуЧаЕтСя)");
    }

    public void sendMessage1(MessageCreateEvent event){
        event.getChannel().sendMessage("111");
    }
}