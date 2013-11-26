package com.gamepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gamepad.lib.GPC;
import com.gamepad.lib.update.AutoUpdater;

public class MainActivity extends Activity
{
    static GPC gpc;
    static Context appContext;
    static final AutoUpdater updater = new AutoUpdater();

    public static Context getContext()
    {
        return appContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = getApplicationContext();

       ImageView btnHost = (ImageView) findViewById(R.id.hostGame);
        btnHost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openHostScreen();
            }
        });

        ImageView btnLib = (ImageView)findViewById(R.id.joinGame);
        btnLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameLibrary();
            }
        });

        gpc = new GPC();
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                updater.getInventory();
                updater.getData();
                if( updater.hasUpdates() ) {
                    updater.doUpdate();
                }
                return null;
            }
        };
        task.execute();
    }

    private void openGameScreen()
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void openHostScreen()
    {
      Intent intent = new Intent(this, HostActivity.class);
      startActivity(intent);
    }

    private void openGameLibrary()
    {
        Intent intent = new Intent(this, GameLibrary.class);
        startActivity(intent);
    }

    private void openNetworkDebugActivity()
    {
        Intent intent = new Intent(this, NetworkDebugActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public static GPC getBaseGPC()
    {
        return gpc;
    }

}
