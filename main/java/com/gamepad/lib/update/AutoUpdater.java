package com.gamepad.lib.update;

import android.content.Context;
import android.util.Log;

import com.gamepad.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AutoUpdater {

    //the url to the info file (saved as a json file)
    public static final String INFO_FILE = "https://dl.dropboxusercontent.com/u/19280458/updateInfo.json";

    //the url used for an internet check
    public static final String INTERNET_CHECK_URL = "http://google.de";

    private ArrayList<AvailableGame> games;
    private ArrayList<AvailableGame> inventory;
    private ArrayList<AvailableGame> updateList;

    //initialized a new instance of the autoupdater class
    public AutoUpdater()
    {
        games = new ArrayList<AvailableGame>();
        updateList = new ArrayList<AvailableGame>();
    }

    public static boolean serializeObject(Object pObject, Context pContext) {
        try
        {
            File folderToWrite = pContext.getDir("GamePadData", Context.MODE_PRIVATE); // Activity is an instance of Context -> MainActivity.this.getDir(...);
            File fileToWrite = new File(folderToWrite, "inventory.bin");

            FileOutputStream fileOutput = new FileOutputStream(fileToWrite);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

            objectOutput.writeObject(pObject);

            objectOutput.close();

            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static Object deserializeObject()
    {
        try {
            File folderToRead = MainActivity.getContext().getDir("GamePadData", Context.MODE_PRIVATE);
            File fileToRead = new File(folderToRead, "inventory.bin");

            if(fileToRead.exists())
            {
                FileInputStream fileInput = new FileInputStream(fileToRead);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);

                Object tObject = objectInput.readObject();

                objectInput.close();

                return tObject;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Downloads via the http protocol the page source to a string
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
            Context test = MainActivity.getContext();
            serializeObject(inventory, MainActivity.getContext());

        }catch(JSONException e){
            Log.e("AutoUpdate", "Error with JSON", e);
        }
        catch(IOException e){
            Log.e("AutoUpdate", "Error with the download", e);
        }
    }

    public void getInventory()
    {
        try{
            inventory = (ArrayList<AvailableGame>)deserializeObject();
        }
        catch(Exception ex){}
        if(inventory == null) {
            inventory = new ArrayList<AvailableGame>();
        }
    }

    public AvailableGame getGameFromName(String name)
    {
        for(AvailableGame game  : inventory)
        {
            if(game.getName().equals(name))
            {
                return game;
            }
        }
        return null;
    }

    public boolean hasUpdates()
    {
        for(int i = 0; i < games.size(); i++) {

            AvailableGame game = games.get(i);
            String gameName = game.getName();
            int gameVersion = game.getVersion();

            Log.e("Game check", "Name: " + gameName);
            Log.e("Game check", "Server version: " + game.getVersion());

            if(gameName != null) {
                Log.e("Game check", "Local version: " + gameVersion);

                if(game.getVersion() > gameVersion) {
                    Log.e("Game check", "Update available");
                    updateList.add(game);
                }
                else if (game.getVersion() <= gameVersion) {
                    Log.e("Game check", "Up to date");
                }

            } else {
                Log.e("Game check", "Local version: none");
                Log.e("Game check", "Update available");
                updateList.add(game);
            }
        }

        if(updateList.size() > 0) {
            return true;
        }

        return false;
    }

    public void doUpdate()
    {
        for(int i = 0; i < updateList.size(); i++)
        {
            AvailableGame game = updateList.get(i);

            // Download file
            // - Success update inventory
            // downloader.addToQuene(game);
        }
        // downloader.start();
    }

    public void updateInventory(AvailableGame game)
    {
        // Test, but can't call
        //game.setVersion(2);
        inventory.add(game);
        // Serialize here or in the end of doUpdate.
        serializeObject(inventory, MainActivity.getContext());
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
