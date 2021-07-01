package nl.nickkoepr.mcserverbot.discordbot.events;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import nl.nickkoepr.mcserverbot.embed.EmbedCreator;

public class ServerInfoEvent extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent e) {
        if (e.getGuild() == null) return;

        if (e.getName().equals("serverinfo")) {
            e.replyEmbeds(new EmbedCreator().createEmbed().build()).queue();
        }
    }
}
