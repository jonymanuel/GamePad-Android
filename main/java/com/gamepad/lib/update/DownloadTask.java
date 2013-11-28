package com.gamepad.lib.update;

import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Fabian on 18.11.13.
 */
public class DownloadTask extends AsyncTask
{
    private Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] param) {
    return null;
    }
}
