package nl.nickkoepr.mcserverbot.utils;

public class InfoUtil {

    private static String token;
    private static String serverIP;
    private static short serverPort;
    private static String prefix;
    private static int maxPlayersShown;

    public static String getToken() {
        return token;
    }

    public static String getServerIP() {
        return serverIP;
    }

    public static short getServerPort() {
        return serverPort;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static int getMaxPlayersShown() {
        return maxPlayersShown;
    }

    public static void setToken(String token) {
        InfoUtil.token = token;
    }

    public static void setServerIP(String serverIP) {
        InfoUtil.serverIP = serverIP;
    }

    public static void setServerPort(short serverPort) {
        InfoUtil.serverPort = serverPort;
    }

    public static void setPrefix(String prefix) {
        InfoUtil.prefix = prefix;
    }

    public static void setMaxPlayersShown(int maxPlayersShown) {
        InfoUtil.maxPlayersShown = maxPlayersShown;
    }
}

