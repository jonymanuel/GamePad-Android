package com.gamepad.lib.update;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Fabian on 12.11.13.
 */
public class AutoUpdater {

    //the url to the info file (saved as a json file)
    public static final String INFO_FILE = "https://dl.dropboxusercontent.com/u/19280458/updateInfo.json";

    //the url used for an internet check
    public static final String INTERNET_CHECK_URL = "http://google.de";

    private ArrayList<AvailableGame> games;

    //initialized a new instance of the autoupdater class
    public AutoUpdater()
    {
        games = new ArrayList<AvailableGame>();
    }

    //downloads via the http protocol the page source to a string
    private static String downloadHttp(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(15 * 1000);
        connection.setUseCaches(false);
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null){
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();
    }

    // Converts a json array to an arraylist with json objects
    private ArrayList<JSONObject> getArrayListFromJsonArray(JSONArray jArr)
    {
        ArrayList<JSONObject> result = new ArrayList<JSONObject>();
        for(int i = 0;i<jArr.length(); i++)
        {
            try
            {
                result.add(jArr.getJSONObject(i));
            }
            catch(JSONException ex)
            {

            }
        }
        return result;
    }

    //gets all the games that are currently in the game list
    public ArrayList<AvailableGame> getGames()
    {
        return games;
    }

    //get the data for the auto updater class including all games for the core
    public void getData()
    {
        try{
            //download the content from the update info file
            String data = downloadHttp(new URL(INFO_FILE));

            //parse the json code
            JSONObject json = new JSONObject(data);

            //get the gamelist
            JSONArray jArr = json.getJSONArray("GameList");

            //convert the json array to an arraylist with json objects
            ArrayList<JSONObject> jObjects = getArrayListFromJsonArray(jArr);

            //walk through the elements and add new available games to the list in this class
            for(JSONObject jObject : jObjects)
            {
                String name = jObject.getString("Name");
                String versionName = jObject.getString("VersionName");
                int version = jObject.getInt("Version");
                String downloadUrl = jObject.getString("DownloadUrl");
                AvailableGame theGame = new AvailableGame(name, versionName, version, downloadUrl);
                games.add(theGame);
            }


        }catch(JSONException e){
            Log.e("AutoUpdate", "Error with JSON", e);
        }
        catch(IOException e){
            Log.e("AutoUpdate", "Error with the download", e);
        }
    }

    //Checks if there is an internet connection
    public Boolean hasInternetConnection()
    {
        try
        {
            downloadHttp(new URL(INTERNET_CHECK_URL));
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
