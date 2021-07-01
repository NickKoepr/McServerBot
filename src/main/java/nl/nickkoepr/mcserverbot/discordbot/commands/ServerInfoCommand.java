package nl.nickkoepr.mcserverbot.discordbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.nickkoepr.mcserverbot.embed.EmbedCreator;
import nl.nickkoepr.mcserverbot.utils.InfoUtil;

public class ServerInfoCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;

        String message = e.getMessage().getContentRaw();

        if (message.equals(InfoUtil.getPrefix() + "serverinfo")) {
            e.getChannel().sendMessage(new EmbedCreator().createEmbed().build()).queue();
        }
    }
}
