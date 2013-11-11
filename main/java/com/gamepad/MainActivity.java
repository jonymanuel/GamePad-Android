package com.gamepad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gamepad.lib.GPC;

public class MainActivity extends Activity
{
    static GPC gpc;
    static Context appContext;

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

        Button btnNetworkDebug = (Button) findViewById(R.id.button_network_debug);
        btnNetworkDebug.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openNetworkDebugActivity();
            }
        });

        Button btnGameScreen = (Button)findViewById(R.id.button_host);
        btnGameScreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGameScreen();
            }
        });

        gpc = new GPC();
    }

    private void openGameScreen()
    {
        Intent intent = new Intent(this, GameActivity.class);
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
