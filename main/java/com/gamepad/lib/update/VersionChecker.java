package com.gamepad.lib.update;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("ALL")
public class VersionChecker {

    /* release information(new version) */
    public static final String INFO_FILE = "https://dl.dropboxusercontent.com/u/19280458/updateInfo.json";
    private int currentVersionCode;         /* code version set in the AndroidManifest.xml file */
    private String currentVersionName;    /*version name in AndroidManifest.xml */
    private int latestVersionCode;      /*last Available version of the application.*/
    private String latestVersionName;   /*Version name in AndroidManifest.xml */
    private String downloadURL;     /* download link (latest version)*/

    /**
     connecting to the network, download the file and convert it to String.
     */
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

    public void getData()
    {  /* Obtain information about The current version.*/
        try{
            // Local data


            /* Remote Info */
            String data = downloadHttp(new URL(INFO_FILE));
            JSONObject json = new JSONObject(data);
            latestVersionCode = json.getInt("versionCode");
            latestVersionName = json.getString("versionName");
            downloadURL = json.getString("downloadURL");
            Log.d("AutoUpdate", "Data collected successfully");
        }catch(JSONException e){
            Log.e("AutoUpdate", "Error with JSON", e);
        }
        catch(IOException e){
            Log.e("AutoUpdate", "Error with the download", e);
        }
    }

    public boolean isNewVersionAvailable() {    /*compare the current version with the latest.*/
        return getLatestVersionCode() > getCurrentVersionCode();
    }

    public int getCurrentVersionCode() {    /*Returns the current version code */
        return currentVersionCode;
    }

    public String getCurrentVersionName() { /* Returns the current version name */
        return currentVersionName;
    }

    public int getLatestVersionCode() { /* Returns the code of the latest version. */
        return latestVersionCode;
    }

    public String getLatestVersionName() {  /*Returns the name of the latest available */
        return latestVersionName;
    }

    public String getDownloadURL() {    /*Get download link for the latest version*/
        return downloadURL;
    }

}
