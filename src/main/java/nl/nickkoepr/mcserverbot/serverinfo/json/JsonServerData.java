package nl.nickkoepr.mcserverbot.serverinfo.json;

import nl.nickkoepr.mcserverbot.serverinfo.exceptions.ServerNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class JsonServerData {
    public JSONObject getJsonObject(String serverIP, long serverPort) throws IOException, ParseException, ServerNotFoundException {
        JSONObject jsonObject = null;

        //Get the data from minecraft-api.com
        URL url = new URL("https://minecraft-api.com/api/ping/" + serverIP + "/" + serverPort + "/json");
        //Open scanner.
        Scanner scanner = new Scanner(url.openStream());
        //Create StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        //Get the content from the page.
        while (scanner.hasNext()) {
            stringBuffer.append(scanner.nextLine());
        }
        //Close the scanner.
        scanner.close();
        //Result from the page.
        String result = stringBuffer.toString();
        //If the result starts with 'Failed', the server ip and/or port is wrong, or the website cannot find the server.
        if (result.startsWith("Failed")) {
            throw new ServerNotFoundException("Server not found!");
        } else {
            //Create the JSONObject if the website has found the server.
            jsonObject = (JSONObject) new JSONParser().parse(result);
        }
        return jsonObject;
    }


}
