package com.github.SunnySt4r.discordBotForValorantServer.Listeners;

import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MyMessageCreateListener implements MessageCreateListener {

    private final EmbedBuilder findTeamText = new EmbedBuilder()
            .setImage(new File("img/913197_a21c8d86-c010-40e2-8bf1-b8de4b2d58a9.jpg"))
            .setColor(Color.GREEN);

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        User user = event.getMessageAuthor().asUser().get();
        ServerTextChannel textChannel = event.getServerTextChannel().get();
        if(textChannel.getId() == 1027972406796627968L && !user.isYourself() && !user.isBot()){
            findTeamStart(event);
            event.getMessage().delete();
        }else if(textChannel.getId() == 722261581290930199L && !user.isYourself() && !user.isBot()){
            if(user.getId() == 694899865670647848L){
                try {
                    messageForMatvey(event);
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else if(user.getId() == 282914050566914049L){
                event.getChannel().sendMessage("Привет, хозяин.\nСпасибо, что создал меня.");
            }
        }
    }

    private void messageForMatvey(MessageCreateEvent event) throws ExecutionException, InterruptedException {
        User user = event.getMessageAuthor().asUser().get();
        user.openPrivateChannel().get().sendMessage("ЛОХ ПоЛуЧаЕтСя)");
    }

    public void findTeamStart(MessageCreateEvent event){
        User user = event.getMessageAuthor().asUser().get();

        if(user.getConnectedVoiceChannel(event.getServer().get()).isPresent()){
            ServerVoiceChannel voiceChannel = user.getConnectedVoiceChannel(event.getServer().get()).get();

            Message message = findTeamCreateMessage(
                    event.getChannel(),
                    event.getMessageContent(),
                    voiceChannel
            );

            voiceChannel.addServerVoiceChannelMemberJoinListener(joinEvent -> {
                if(5 - voiceChannel.getConnectedUserIds().size() == 0){
                    joinEvent.getApi().getThreadPool().getScheduler().schedule(() ->{
                        if(5 - voiceChannel.getConnectedUserIds().size() == 0){
                            message.delete();
                        }
                    }, 30, TimeUnit.SECONDS);
                }else{
                    messageEdit(message, voiceChannel);
                }
            });

            voiceChannel.addServerVoiceChannelMemberLeaveListener(leaveEvent ->{
                if ((5 - voiceChannel.getConnectedUserIds().size()) == 5){
                    message.delete();
                }else{
                    messageEdit(message, voiceChannel);
                }
            });
        }else{
            event.getMessage().delete();
            event.getChannel().sendMessage("Ошибка." +
                    "\nСначала подключитесь к голосовому каналу \"Создать команду\"").thenAccept(message ->{
                        message.getApi().getThreadPool().getScheduler().schedule(() -> {
                            message.delete();
                }, 10, TimeUnit.SECONDS);
            });
        }
    }

    public Message findTeamCreateMessage(TextChannel channel, String text, ServerVoiceChannel voiceChannel){
        findTeamText.setTitle("Нужно еще " + (5 - voiceChannel.getConnectedUserIds().size()))
                .setDescription(text)
                .removeAllFields()
                .addField("Игроки:", usersToBeautifulString(voiceChannel))
                .addField("Присоединиться: ", "https://discord.gg/"
                        +voiceChannel.createInviteBuilder().create().join().getCode());

        return new MessageBuilder()
                .setEmbeds(findTeamText)
                .send(channel)
                .join();
    }

    public String usersToBeautifulString(ServerVoiceChannel voiceChannel){
        StringBuilder users = new StringBuilder();
        for(User user:voiceChannel.getConnectedUsers()){
            users.append(":medal: ").append(user.getNicknameMentionTag()).append("\n");
        }
        return users.toString();
    }

    public void messageEdit(Message message, ServerVoiceChannel voiceChannel){
        message.createUpdater().setEmbed(findTeamText
                .setTitle("Нужно еще " + (5 - voiceChannel.getConnectedUserIds().size()) + "игрока")
                .removeAllFields()
                .addField("Игроки:", usersToBeautifulString(voiceChannel))
                .addField("Присоединиться: ", "https://discord.gg/"
                        +voiceChannel.createInviteBuilder().create().join().getCode())
        ).applyChanges();
    }
}
