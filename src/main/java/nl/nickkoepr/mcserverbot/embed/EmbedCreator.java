package nl.nickkoepr.mcserverbot.embed;

import net.dv8tion.jda.api.EmbedBuilder;
import nl.nickkoepr.mcserverbot.serverinfo.exceptions.ServerNotFoundException;
import nl.nickkoepr.mcserverbot.serverinfo.mcserver.McServer;
import nl.nickkoepr.mcserverbot.serverinfo.mcserver.McServerManager;
import nl.nickkoepr.mcserverbot.utils.InfoUtil;

import java.awt.*;

public class EmbedCreator {

    public EmbedBuilder createEmbed() {

        //Make a EmbedBuilder.
        EmbedBuilder embedBuilder = new EmbedBuilder();
        //Get the Minecraft server IP and port.
        String serverIP = InfoUtil.getServerIP();
        short serverPort = InfoUtil.getServerPort();

        try {
            //Try to create a new MinecraftServer object.
            McServer mcServer = McServerManager.getMcServer(serverIP, serverPort);

            //Adding information to the embed.
            embedBuilder.setColor(Color.GREEN);
            embedBuilder.setTitle("**Server information**");
            embedBuilder.addField("**Server ip: **", serverIP + ":" + serverPort, false);
            embedBuilder.addField("**Server version: **", mcServer.getServerVersion() + "", false);
            embedBuilder.addField("**Player count:**", mcServer.getOnlinePlayers() + "/" + mcServer.getMaxPlayers(), false);

            //Check if the server has any players online.
            if (mcServer.getPlayers().size() != 0) {
                //If the server has players online, add them to the embed.
                String playerMessage = "";
                int maxPlayersShown = InfoUtil.getMaxPlayersShown();
                int maxPlayers = 0;
                //Check if the value of maxPlayersShown is -1. If the value is -1, it will put all the players on the server into the embed.
                // If the value is not -1, it will only add players to the maximum that the user has provided.
                if (maxPlayersShown == -1) {
                    maxPlayers = mcServer.getPlayers().size();
                } else {
                    maxPlayers = maxPlayersShown;
                }
                //Loop through every item in the ArrayList.
                //! If you have a bungeecord server, you will not see any player names on the embed because the api cannot get names from bungeecord servers !
                for (int i = 0; i < maxPlayers && i < mcServer.getPlayers().size(); i++) {
                    //Check if there is only one player online. If there is online player online, add only that player to the embed with a dot at the end.
                    if (mcServer.getPlayers().size() != 1) {
                        //Check if the ArrayList is not at its last value.
                        if (i != mcServer.getPlayers().size() - 1) {
                            //Check if there is one player behind the last player. If there is a player, don't add a comma at the end.
                            if(i != mcServer.getPlayers().size() - 2){
                                //Add the name of the player to playerMessage and add a comma.
                                playerMessage += mcServer.getPlayers().get(i) + ", ";
                            }else{
                                //Add the name of the player to playerMessage without the comma.
                                playerMessage += mcServer.getPlayers().get(i) + " ";
                            }
                        } else {
                            //When the ArrayList is at his last value, add 'and' before his name and add a dot at the end.
                            playerMessage += "and " + mcServer.getPlayers().get(i) + ".";
                        }
                    } else {
                        playerMessage += mcServer.getPlayers().get(i) + ".";
                    }
                }
                //If the value of maxPlayersShown is not -1, add a more players to the end of the message with the count of the remaining players.
                if (maxPlayersShown != -1) {
                    //Check if players have not been added to the message.
                    if (maxPlayers < mcServer.getPlayers().size()) {
                        //Check if there is only one player left. If so, add player and not players to the message.
                        if ((mcServer.getPlayers().size() - maxPlayers) != 1) {
                            playerMessage += " *and " + (mcServer.getPlayers().size() - maxPlayers) + " more players.*";
                        } else {
                            playerMessage += " *and " + (mcServer.getPlayers().size() - maxPlayers) + " more player.*";
                        }
                    }
                }
                //Add the message to the embed.
                embedBuilder.addField("**Players:**", playerMessage, false);
            }
        } catch (ServerNotFoundException e) {
            //Catch the ServerNotFoundException for when a server is not found.
            embedBuilder.clear();
            embedBuilder.setColor(Color.RED);
            embedBuilder.setTitle("**Server not found**");
            embedBuilder.setDescription("**We cannot find the server! Please check if the information is corrent. When it is, there is probably something not right when getting the server information.**");

            System.out.println("Error! Can't get the information from the server with ip: " + serverIP + " port: " + serverPort + " if the information is corrent, there is probably something not right when getting the server information.");

        } catch (Exception e) {
            //Catch any other error's that may occur.
            embedBuilder.clear();
            embedBuilder.setColor(Color.RED);
            embedBuilder.setTitle("**Error**");
            embedBuilder.setDescription("**An unexpected error has occurred.**");

            System.out.println("An unexpected error has occurred: \n ");
            e.printStackTrace();
        }
        //Return the builed embed.
        return embedBuilder;
    }

}
