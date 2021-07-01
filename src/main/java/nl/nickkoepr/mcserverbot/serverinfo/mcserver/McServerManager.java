package nl.nickkoepr.mcserverbot.serverinfo.mcserver;

import nl.nickkoepr.mcserverbot.serverinfo.exceptions.ServerNotFoundException;
import nl.nickkoepr.mcserverbot.serverinfo.json.JsonServerData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class McServerManager {

    public static McServer getMcServer(String serverIP, short serverPort) throws IOException, ParseException, ServerNotFoundException {
        JSONObject serverInformation = new JsonServerData().getJsonObject(serverIP, serverPort);

        //Data to get from the JSONObject.
        short port;
        int maxPlayers, onlinePlayers;
        String ip, serverVersion;
        ArrayList<String> players = new ArrayList<String>();

        //Get the JSONObjects for the data players and version.
        JSONObject playerCountData = (JSONObject) serverInformation.get("players");
        JSONObject serverVersionData = (JSONObject) serverInformation.get("version");

        //Get maxPlayers, onlinePlayers and serverVersion from the JSONObjects.
        maxPlayers = (int) Integer.parseInt(playerCountData.get("max").toString());
        onlinePlayers = (int) Integer.parseInt(playerCountData.get("online").toString());
        serverVersion = (String) serverVersionData.get("name");

        //When players are online, get their data into a JSONArray.
        JSONArray playerOnlineData = (JSONArray) playerCountData.get("sample");
        //Check if there is a playerlist.
        if (playerOnlineData != null) {
            //Create Iterator.
            Iterator playerOnlineiterator = playerOnlineData.iterator();
            //Loop through the data from the Iterator.
            while (playerOnlineiterator.hasNext()) {
                //Get the JSONObject with the player data.
                JSONObject currentPlayer = (JSONObject) playerOnlineiterator.next();
                //Put the player UUID and name into a ArrayList.
                players.add((String) currentPlayer.get("name"));
            }
        }

        //Create new McServer object.
        return new McServer(serverIP, serverPort, maxPlayers, onlinePlayers, players, serverVersion);
    }
}
