package nl.nickkoepr.mcserverbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import nl.nickkoepr.mcserverbot.discordbot.commands.ServerInfoCommand;
import nl.nickkoepr.mcserverbot.discordbot.events.ServerInfoEvent;
import nl.nickkoepr.mcserverbot.serverinfo.exceptions.ServerNotFoundException;
import nl.nickkoepr.mcserverbot.serverinfo.json.SettingsJson;
import nl.nickkoepr.mcserverbot.utils.InfoUtil;
import org.json.simple.parser.ParseException;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class McServerBot {

    public static void main(String[] args) throws LoginException, IOException, ParseException, ServerNotFoundException {

        //Get the settings that the user provided.
        SettingsJson settings = new SettingsJson();
        JDA jda = null;
        JDABuilder jdaBuilder = null;
        CommandListUpdateAction slashCommand = null;

        //Set all the data for the bot.
        InfoUtil.setToken(settings.getToken());
        InfoUtil.setServerIP(settings.getServerIP());
        InfoUtil.setServerPort(settings.getServerPort());
        InfoUtil.setMaxPlayersShown(settings.getMaxPlayersShown());
        InfoUtil.setPrefix(settings.getPrefix());

        jdaBuilder = JDABuilder.createDefault(InfoUtil.getToken());

        jdaBuilder.addEventListeners(new ServerInfoCommand());
        jdaBuilder.addEventListeners(new ServerInfoEvent());

        jda = jdaBuilder.build();

        //Create the / command. 'These commands take up to an hour to be activated after creation/update/delete' according to JDA!
        slashCommand = jda.updateCommands();
        slashCommand.addCommands(new CommandData("serverinfo", "Get the server information for the server " + InfoUtil.getServerIP() + ":" + InfoUtil.getServerPort() + "."));
        slashCommand.queue();

        System.out.println("Logged in as: " + jda.getSelfUser().getAsTag() + "!");
    }
}
