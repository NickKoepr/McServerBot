package nl.nickkoepr.mcserverbot.serverinfo.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class SettingsJson {

    File settingsJson = new File("settings.json");
    JSONObject jsonFile = null;

    String token;
    String serverIP;
    short serverPort;
    String prefix;
    int maxPlayersShown;


    public SettingsJson() throws IOException, ParseException {

        //Check if the file exists, when the file doens't exists, start the 'setup'.
        if (!settingsJson.exists()) {

            //Create a scanner.
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hello! Before you can use the bot, you have to provide some information." +
                    " You can change this later in the settings.json file.");

            System.out.println("Please paste your Discord bot token:");
            //Get the token that the user provided.
            token = scanner.nextLine();

            System.out.println("Please paste your server ip:");
            //Get the server ip that the user provided.
            serverIP = scanner.nextLine();

            System.out.println("Please paste the port of your server:");
            //Get the server port that the user provided.
            serverPort = Short.parseShort(scanner.nextLine());

            System.out.println("Please paste the prefix that you want to use:");
            //Get the prefix that the user provided.
            prefix = scanner.nextLine();

            System.out.println("Please enter the number of players you want to show if someone uses the command (If you want to show every player," +
                    "you can set the value to -1 ( This is on your own responsibility! There is a chance that the bot will have a hard time loading " +
                    "to many player names!):");
            //Get the max players shown that the user provided.
            maxPlayersShown = Integer.parseInt(scanner.nextLine());

            System.out.println("That's all! You can always change this information in the settings.json file.");
            //Close the scanner and create the file.
            scanner.close();
            if (settingsJson.createNewFile()) {
                System.out.println("settings.json created.");
            } else {
                System.out.println("Could not create the settings.json file! Please try again!");
            }

            //Set the information to the jsonFile object.
            jsonFile = new JSONObject();

            jsonFile.put("token", token);
            jsonFile.put("serverIP", serverIP);
            jsonFile.put("serverPort", serverPort);
            jsonFile.put("prefix", prefix);
            jsonFile.put("maxPlayersShown", maxPlayersShown);

            //Get all the information from the jsonFile object and put it into the settings.json file.
            Files.write(settingsJson.toPath(), jsonFile.toString().getBytes());
        } else {

            //Create scanner.
            Scanner scanner = new Scanner(settingsJson);
            //Create StringBuffer.
            StringBuffer stringBuffer = new StringBuffer();
            //Get the content from the file.
            while (scanner.hasNext()) {
                stringBuffer.append(scanner.nextLine());
            }
            //Close the scanner.
            scanner.close();
            //Result from the page.
            String result = stringBuffer.toString();
            //Turn the result into a JSONObject.
            jsonFile = (JSONObject) new JSONParser().parse(result);

            //Get the information from the file and set the information to the variables.
            token = (String) jsonFile.get("token");
            serverIP = (String) jsonFile.get("serverIP");
            serverPort = Short.parseShort(jsonFile.get("serverPort").toString());
            prefix = (String) jsonFile.get("prefix");
            maxPlayersShown = Integer.parseInt(jsonFile.get("maxPlayersShown").toString());
        }
    }

    public String getToken() {
        return token;
    }

    public String getServerIP() {
        return serverIP;
    }

    public short getServerPort() {
        return serverPort;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getMaxPlayersShown() {
        return maxPlayersShown;
    }
}
