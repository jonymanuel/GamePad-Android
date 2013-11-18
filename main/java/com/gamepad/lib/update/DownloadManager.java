package com.gamepad.lib.update;

import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;

import com.gamepad.MainActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Fabian on 18.11.13.
 */
public class DownloadManager
{
    static Queue<String> downloadQueue;
    static DownloadTask currentTask;
    static Integer currenProgress;
    static String currentDownload;
    static Boolean isRunning;

    public static void queueNewDownloadFile(String url)
    {
        if (downloadQueue == null)
        {
            downloadQueue = new LinkedList<String>();
        }

        downloadQueue.add(url);
    }

    public void startDownloading()
    {
        AsyncTask task = new AsyncTask()
        {
            @Override
            protected Object doInBackground(Object[] objects)
            {
                downloadNextFile();
                return null;
            }
        }.execute();
    }

    private static String getFileNameFromUrl(String url)
    {
        String[] tempSplitArray = url.split("/");
        return tempSplitArray[tempSplitArray.length];
    }

    private static String downloadFile(String downloadUrl)
    {
        String toDownload = downloadUrl;
        String fileName = getFileNameFromUrl(toDownload);

        // take CPU lock to prevent CPU from going off if the user
        // presses the power button during download
        PowerManager pm = (PowerManager) MainActivity.getContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DownloadManager");
        wl.acquire();

        try
        {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try
            {
                URL url = new URL(toDownload);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                {
                    return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream("/sdcard/" + fileName);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1)
                {
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0)
                    {
                        currenProgress = ((int) (total * 100 / fileLength));
                        currentDownload = "Downloading " + fileName;
                    }
                    output.write(data, 0, count);
                }
            }
            catch (Exception e)
            {
                return e.toString();
            }
            finally
            {
                try
                {
                    if (output != null)
                    {
                        output.close();
                    }
                    if (input != null)
                    {
                        input.close();
                    }
                }
                catch (IOException ignored)
                {
                }

                if (connection != null)
                {
                    connection.disconnect();
                }
            }
        }
        finally
        {
            wl.release();
        }
        return null;
    }

    private static void downloadNextFile()
    {
        if (downloadQueue == null)
        {
            downloadQueue = new LinkedList<String>();
        }
        if (downloadQueue.size() >= 1)
        {
            if (currentTask == null)
            {
                currentTask = new DownloadTask(MainActivity.getContext());
            }
            downloadFile(downloadQueue.remove());
            downloadNextFile();
        }
    }


}
