package nl.nickkoepr.mcserverbot.serverinfo.mcserver;

import java.util.ArrayList;

public class McServer {

    short serverPort;
    int maxPlayers, onlinePlayers;
    String serverIP, serverVersion;
    ArrayList<String> players;

    public McServer(String serverIP, short serverPort, int maxPlayers, int onlinePlayers, ArrayList<String> players, String serverVersion) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.maxPlayers = maxPlayers;
        this.onlinePlayers = onlinePlayers;
        this.serverVersion = serverVersion;
        this.players = players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public short getServerPort() {
        return serverPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }
}
